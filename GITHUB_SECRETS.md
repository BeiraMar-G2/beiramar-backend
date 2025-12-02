# Configuração de GitHub Secrets para CI/CD com ALB

## Secrets Obrigatórios

Para que o workflow CI/CD funcione corretamente, você precisa configurar os seguintes secrets no repositório GitHub:

### 1. **EC2_SSH_KEY**
- **Descrição**: Chave SSH privada para acessar a instância EC2 do backend
- **Valor**: Conteúdo completo do arquivo `.pem` da sua chave SSH
- **Exemplo**: 
  ```
  -----BEGIN RSA PRIVATE KEY-----
  MIIEpAIBAAKCAQEA...
  -----END RSA PRIVATE KEY-----
  ```

### 2. **BACKEND_HOST**
- **Descrição**: IP ou hostname da instância EC2 onde o backend está rodando
- **Valor**: IP privado da sua EC2 (ex: `10.0.2.50` ou o IP público se acessível)
- **Nota**: Pode ser um IP privado se a instância que roda o GitHub Actions tem acesso a ele

### 3. **BACKEND_USER**
- **Descrição**: Usuário SSH para acessar a instância EC2
- **Valor**: Geralmente `ubuntu` ou `ec2-user` (depende da AMI)
- **Exemplo**: `ubuntu`

### 4. **DB_HOST**
- **Descrição**: Host do banco de dados MySQL
- **Valor**: IP privado ou nome da instância RDS/EC2 com MySQL
- **Exemplo**: `10.0.3.100` ou `beiramar-db.internal`

### 5. **ALB_DNS_NAME**
- **Descrição**: DNS name do Application Load Balancer
- **Valor**: Nome DNS público do ALB (ex: `alb-beira-mar-123456789.us-east-1.elb.amazonaws.com`)
- **Como obter**: 
  ```bash
  # Via AWS CLI
  aws elbv2 describe-load-balancers --names alb-beira-mar --query 'LoadBalancers[0].DNSName' --output text
  ```
  Ou acesse o output do Terraform:
  ```bash
  terraform output alb_dns_name
  ```

## Como Configurar os Secrets

### Via GitHub Web UI:
1. Vá para **Settings** > **Secrets and variables** > **Actions**
2. Clique em **New repository secret**
3. Preenchas as informações:
   - **Name**: Nome exato do secret (ex: `EC2_SSH_KEY`)
   - **Value**: Valor do secret
4. Clique em **Add secret**

### Via GitHub CLI:
```bash
# EC2_SSH_KEY
gh secret set EC2_SSH_KEY < /caminho/para/sua/chave.pem

# BACKEND_HOST
gh secret set BACKEND_HOST --body "10.0.2.50"

# BACKEND_USER
gh secret set BACKEND_USER --body "ubuntu"

# DB_HOST
gh secret set DB_HOST --body "10.0.3.100"

# ALB_DNS_NAME
gh secret set ALB_DNS_NAME --body "alb-beira-mar-123456789.us-east-1.elb.amazonaws.com"
```

## Fluxo do CI/CD Atualizado

1. **Checkout**: Clona o repositório
2. **Configurar JDK**: Instala Java 21 com cache Maven
3. **Configurar BD e CORS**: 
   - Atualiza `spring.datasource.url` com `DB_HOST`
   - Atualiza `cors.allowed.origins` com `ALB_DNS_NAME`
4. **Build JAR**: Compila o projeto com Maven
5. **Renomear JAR**: Define nome fixo `beiramar-api.jar`
6. **Copiar via SCP**: Envia o JAR para `/tmp/` na EC2
7. **Deploy**:
   - Move JAR para `/usr/share/api/`
   - Para containers Docker Compose
   - Recria containers com novo JAR
   - Valida status dos containers

## Verificação

Para testar se tudo está funcionando:

```bash
# 1. Acesse via ALB (frontend)
curl http://alb-beira-mar-123456789.us-east-1.elb.amazonaws.com

# 2. Acesse a API (backend)
curl http://alb-beira-mar-123456789.us-east-1.elb.amazonaws.com/api/health

# 3. Verifique os logs no backend
ssh -i sua-chave.pem ubuntu@10.0.2.50
sudo docker-compose -f compose.yaml logs -f beiramar-api
```

## Segurança

⚠️ **Importante:**
- Nunca compartilhe a chave SSH privada
- Revoke e regenere chaves se comprometidas
- Use variáveis de ambiente para dados sensíveis no `application.properties`
- Considere usar AWS Secrets Manager ou HashiCorp Vault para produção

## Troubleshooting

### Erro: "Context access might be invalid"
- Os secrets ainda não foram criados no GitHub
- Configure todos os 5 secrets mencionados acima

### Erro: "Connection refused"
- Verifique se `BACKEND_HOST` está correto
- Valide que a instância EC2 está rodando
- Confirme que o Security Group permite SSH (porta 22)

### Erro: "Permission denied (publickey)"
- Verifique se `EC2_SSH_KEY` tem o formato correto
- Confirme que o arquivo `.pem` corresponde à instância

### JAR não atualiza
- Verifique se o caminho `/usr/share/api/` existe e tem permissões
- Confirme que o Docker Compose está referenciando o caminho correto
- Valide os logs: `sudo docker-compose logs -f`

## Exemplo de Configuração no docker-compose.yaml

```yaml
version: '3.8'

services:
  beiramar-api:
    image: openjdk:21-jdk-slim
    container_name: beiramar-api
    volumes:
      - /usr/share/api/beiramar-api.jar:/app/beiramar-api.jar
    ports:
      - "8080:8080"
    command: java -jar /app/beiramar-api.jar
    networks:
      - beira-mar-network

networks:
  beira-mar-network:
    driver: bridge
```
