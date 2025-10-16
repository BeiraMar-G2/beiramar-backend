import os
import json
import pika
from sendgrid import SendGridAPIClient
from sendgrid.helpers.mail import Mail
from dotenv import load_dotenv

load_dotenv()

# Configurações
RABBITMQ_USER = os.getenv("RABBITMQ_USER", "myuser")
RABBITMQ_PASS = os.getenv("RABBITMQ_PASS", "secret")
RABBITMQ_HOST = os.getenv("RABBITMQ_HOST", "rabbitmq")
RABBITMQ_QUEUE = os.getenv("RABBITMQ_QUEUE", "email-queue")

SENDGRID_API_KEY = os.getenv("SENDGRID_API_KEY")
FROM_EMAIL = os.getenv("FROM_EMAIL", "beiramar.estetica@gmail.com")
FROM_NAME = os.getenv("FROM_NAME", "Beira Mar Estética")

def send_email(to_email, codigo):
    """Envia o e-mail usando SendGrid"""
    subject = "Código de recuperação de senha"
    html_content = f"""
    <html>
      <body style="font-family: Arial, sans-serif; text-align: center; padding: 20px;">
        <h2 style="color: #333;">Recuperação de Senha</h2>
        <p>Olá,</p>
        <p>Seu código de recuperação é:</p>
        <div style="font-size: 32px; font-weight: bold; color: #007BFF;">{codigo}</div>
        <p style="font-size: 14px; color: #777;">Se você não solicitou isso, ignore esta mensagem.</p>
      </body>
    </html>
    """
    message = Mail(
        from_email=(FROM_EMAIL, FROM_NAME),
        to_emails=to_email,
        subject=subject,
        html_content=html_content
    )
    try:
        sg = SendGridAPIClient(SENDGRID_API_KEY)
        response = sg.send(message)
        print(f"✅ E-mail enviado para {to_email} - Código {codigo}")
    except Exception as e:
        print(f"❌ Erro ao enviar e-mail: {e}")

def callback(ch, method, properties, body):
    """Função chamada ao consumir mensagem da fila"""
    try:
        data = json.loads(body)
        email = data.get("email")
        codigo = data.get("codigo")
        print(f"📩 Mensagem recebida: {data}")
        send_email(email, codigo)
        ch.basic_ack(delivery_tag=method.delivery_tag)
    except Exception as e:
        print(f"❌ Erro ao processar mensagem: {e}")
        ch.basic_nack(delivery_tag=method.delivery_tag, requeue=False)

def main():
    """Conecta ao RabbitMQ e consome mensagens"""
    credentials = pika.PlainCredentials(RABBITMQ_USER, RABBITMQ_PASS)
    connection = pika.BlockingConnection(
        pika.ConnectionParameters(host=RABBITMQ_HOST, credentials=credentials)
    )
    channel = connection.channel()
    channel.queue_declare(queue=RABBITMQ_QUEUE, durable=True)
    channel.basic_qos(prefetch_count=1)
    channel.basic_consume(queue=RABBITMQ_QUEUE, on_message_callback=callback)
    print(f"🚀 Aguardando mensagens da fila '{RABBITMQ_QUEUE}'...")
    channel.start_consuming()

if __name__ == "__main__":
    main()
