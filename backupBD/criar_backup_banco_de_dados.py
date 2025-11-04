#!/usr/bin/env python3
import os
import sys
import subprocess
import gzip
import shutil
import json
import logging
from datetime import datetime
from pathlib import Path

try:
    import boto3
    import pika
except Exception:
    print("Dependências ausentes. Instale: pip install boto3 pika", file=sys.stderr)
    sys.exit(1)


def load_env(path):
    env = {}
    if not os.path.isfile(path):
        return env
    with open(path, "r") as f:
        for line in f:
            line = line.strip()
            if not line or line.startswith("#"):
                continue
            # Aceitar linhas com ou sem 'export '
            if line.startswith("export "):
                line = line[len("export "):].strip()
            if "=" in line:
                k, v = line.split("=", 1)
                env[k.strip()] = v.strip().strip('\'"')
    return env


def setup_logging(log_file):
    logging.basicConfig(
        level=logging.INFO,
        format="%(asctime)s - %(levelname)s - %(message)s",
        handlers=[logging.FileHandler(log_file), logging.StreamHandler(sys.stdout)],
    )


def run_mysqldump(cfg, out_path):
    cmd = [
        "mysqldump",
        "-h", cfg.get("DB_HOST", "localhost"),
        "-P", str(cfg.get("DB_PORT", "3306")),
        "-u", cfg["DB_USER"],
        f"-p{cfg.get('DB_PASSWORD','')}",
        "--single-transaction",
        "--routines",
        "--triggers",
        "--events",
        "--add-drop-database",
        "--add-drop-table",
        "--comments",
        "--dump-date",
        cfg["DB_NAME"],
    ]
    with open(out_path, "wb") as outf:
        proc = subprocess.run(cmd, stdout=outf, stderr=subprocess.PIPE)
    return proc.returncode, proc.stderr.decode("utf-8", errors="ignore")


def gzip_file(src_path):
    gz_path = f"{src_path}.gz"
    with open(src_path, "rb") as f_in, gzip.open(gz_path, "wb") as f_out:
        shutil.copyfileobj(f_in, f_out)
    os.remove(src_path)
    return gz_path


def upload_s3(local_path, bucket, key, region=None):
    s3 = boto3.client("s3", region_name=region) if region else boto3.client("s3")
    s3.upload_file(local_path, bucket, key)
    return f"s3://{bucket}/{key}"


def send_rabbitmq(cfg, message):
    exchange = cfg.get("RABBITMQ_EXCHANGE", "backup.fanout.exchange")
    user = cfg.get("RABBITMQ_USER", "guest")
    password = cfg.get("RABBITMQ_PASSWORD", "guest")
    host = cfg.get("RABBITMQ_HOST", "localhost")
    port = int(cfg.get("RABBITMQ_PORT", 5672))

    credentials = pika.PlainCredentials(user, password)
    params = pika.ConnectionParameters(host=host, port=port, credentials=credentials)
    conn = pika.BlockingConnection(params)
    ch = conn.channel()
    # Garantir que o exchange existe; em fanout a routing_key é ignorada
    ch.exchange_declare(exchange=exchange, exchange_type="fanout", durable=True)
    ch.basic_publish(
        exchange=exchange,
        routing_key="",
        body=json.dumps(message).encode("utf-8"),
        properties=pika.BasicProperties(delivery_mode=2),
    )
    conn.close()


def main():
    base_dir = Path(__file__).parent
    env_path = base_dir / ".env"
    cfg = load_env(str(env_path))

    required = ["DB_USER", "DB_NAME", "BACKUP_DIR", "S3_BUCKET", "LOG_FILE"]
    for r in required:
        if r not in cfg:
            print(f"Variável {r} não definida em {env_path}", file=sys.stderr)
            sys.exit(2)

    setup_logging(cfg["LOG_FILE"])

    timestamp_human = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    timestamp_file = datetime.now().strftime("%Y%m%d_%H%M%S")
    filename_base = f"backup_{cfg['DB_NAME']}_{timestamp_file}.sql"
    backup_dir = Path(cfg["BACKUP_DIR"])
    backup_dir.mkdir(parents=True, exist_ok=True)
    local_sql_path = str(backup_dir / filename_base)

    status = "Falha"
    s3_path = ""
    sent_filename = filename_base

    try:
        logging.info("Iniciando dump do banco de dados")
        rc, err = run_mysqldump(cfg, local_sql_path)
        if rc != 0:
            logging.error("mysqldump retornou erro: %s", err.strip())
            raise RuntimeError("mysqldump failed")

        logging.info("Compressão do arquivo")
        gz_path = gzip_file(local_sql_path)
        sent_filename = Path(gz_path).name

        logging.info("Enviando para S3")
        s3_key = f"backups/{sent_filename}"
        s3_path = upload_s3(gz_path, cfg["S3_BUCKET"], s3_key, region=cfg.get("AWS_DEFAULT_REGION"))
        status = "Realizado com Sucesso"
        logging.info("Upload concluído: %s", s3_path)

        if cfg.get("REMOVE_LOCAL_AFTER_UPLOAD", "1") in ("1", "true", "True"):
            try:
                os.remove(gz_path)
                logging.info("Arquivo local removido: %s", gz_path)
            except Exception:
                logging.warning("Não foi possível remover o arquivo local: %s", gz_path)

    except Exception as e:
        logging.exception("Erro no processo de backup: %s", e)

    if status == "Realizado com Sucesso":
        message = {
            "nomeArquivo": sent_filename,
            "caminhoArquivo": s3_path,
            "dataBackup": timestamp_human,
            "status": status,
        }
    else:
        message = {
            "nomeArquivo": "Arquivo não exportado",
            "caminhoArquivo": "Backup não carregado para o S3",
            "dataBackup": timestamp_human,
            "status": "Falha ao Realizar Backup",
        }

    try:
        send_rabbitmq(cfg, message)
        logging.info("Mensagem enviada ao RabbitMQ: %s", message)
    except Exception as e:
        logging.exception("Falha ao enviar mensagem ao RabbitMQ: %s", e)

    return 0 if status == "Realizado com Sucesso" else 1


if __name__ == "__main__":
    sys.exit(main())