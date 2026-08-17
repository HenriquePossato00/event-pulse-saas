# EventPulse - Webhook & Notification SaaS

> Plataforma Multi-tenant para Gestão, Despacho e Auditoria de Webhooks e Eventos Assíncronos.

## Sobre o Projeto
O EventPulse é um microsserviço/SaaS projetado para resolver o problema de entrega de notificações e webhooks em sistemas distribuídos. Ele atua como um *broker* intermediário, recebendo eventos das aplicações principais e garantindo a entrega aos endpoints dos clientes com políticas de retentativa, idempotência e registro de auditoria.

## Tecnologias Utilizadas
* **Core API (Regras de Negócio):** Java 17+ e Spring Boot
* **Workers (Processamento Assíncrono):** Node.js e AWS Lambda
* **Banco de Dados Relacional:** PostgreSQL (Tenants, Configurações, Chaves de API)
* **Banco de Dados NoSQL:** AWS DynamoDB (Logs de Auditoria de alta performance)
* **Mensageria e Filas:** AWS SQS & Dead Letter Queues (DLQ)
* **Autenticação & Gateway:** AWS Cognito (JWT) e AWS API Gateway
* **Dashboard do Cliente:** Vite + React

## Decisões de Arquitetura

1. **Separação entre Core e Workers:** O Spring Boot cuida da lógica transacional (planos, chaves, validações). O disparo HTTP real dos webhooks é delegado a funções Lambda (Node.js), que escalam de forma passiva, sem sobrecarregar o servidor principal.
2. **Resiliência de Entrega (Retry Pattern):** Caso o endpoint do cliente esteja offline (ex: erro 500 ou Timeout), o SQS gerencia retentativas automáticas. Após 3 tentativas falhas, o evento é direcionado para uma *Dead Letter Queue (DLQ)* para análise futura.
3. **Banco de Dados PostgreSQL:** O PostgreSQL gerencia tanto os dados relacionais do negócio quanto os logs de disparo.
4. **Segurança de Webhooks:** Cada endpoint cadastrado gera uma chave secreta. Os payloads enviados pelo EventPulse são assinados via HMAC, permitindo que o cliente verifique a autenticidade da requisição.

## Modelagem de Dados

## Modelagem de Dados (PostgreSQL)

O banco relacional é a fonte única de verdade do sistema:

* `tenants`: id (UUID), name, plan_type, api_key_hash, created_at
* `webhook_endpoints`: id (UUID), tenant_id (FK), target_url, secret_key, events[], is_active
* `delivery_logs`: id (UUID), endpoint_id (FK), event_type, payload, status_code, delivery_status (SUCCESS, FAILED), attempts, created_at


## Estratégia de Versionamento
Este projeto simula um ambiente de trabalho real utilizando **GitHub Flow** e **Conventional Commits**:
- A branch `main` representa o ambiente de produção.
- Novas funcionalidades e correções são desenvolvidas em *Feature Branches* (`feat/`, `fix/`, `docs/`).
- O código só é integrado à `main` através de **Pull Requests (PRs)**.