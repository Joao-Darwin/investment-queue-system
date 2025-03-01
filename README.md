<div align="center">
  <img width="200" height="200" src="https://img.icons8.com/3d-fluency/200/investment.png" alt="investment"/>
  <h1 align="center" id="titulo">Investment Queue System</h1> 
</div>

# Índice 
* [Índice](#índice)
* [Sobre o Projeto](#sobreProjeto)
* [Técnologias Usadas](#techs)
* [Executar o Projeto](#execute)
* [Autor](#author)

<h1 id="sobreProjeto">Sobre o Projeto</h1>

O Investment Message Broker é uma solução baseada em microsserviços que permite a criação, processamento e atualização de investimentos de forma assíncrona e confiável. 
Utilizando o RabbitMQ como broker de mensagens, o sistema é composto por três componentes principais: 
- Producer: Desenvolvido em Spring Boot, responsável por receber solicitações e enviar mensagens para a fila além de criar a solicitação no banco de dados
- Middleware: Que contém a configuração do RabbitMQ via Docker Compose
- Consumer: Implementado em TypeScript, que escuta a fila de mensagens e atualiza os investimentos no banco de dados PostgreSQL

Esse modelo garante maior escalabilidade, resiliência e consistência no processamento de investimentos.

### DER

![DER](https://github.com/Joao-Darwin/repoImgs/blob/main/Imgs%20-%20Investment%20Queue%20System/DatabaseDER.png)

## Flow
![Flow](https://github.com/Joao-Darwin/repoImgs/blob/main/Imgs%20-%20Investment%20Queue%20System/Flow.png)

## Estruturas de Pastas do Projeto
```bash
📦 investment-message-broker
├── 📂 producer          # API em Spring Boot que produz mensagens para o RabbitMQ
│   ├── src/            # Código-fonte da aplicação
│   ├── pom.xml         # Configuração do Maven
│   ├── Dockerfile      # Dockerfile para a aplicação Producer
    ├── docker-compose.yml # Execução do Banco de Dados Postgres
│   └── README.md       # Documentação do Producer
│
├── 📂 middleware       # Contém apenas o Docker Compose para o RabbitMQ
│   ├── docker-compose.yml  # Define o serviço do RabbitMQ
│   └── README.md       # Documentação do Middleware
│
├── 📂 consumer         # Script TypeScript que consome mensagens do RabbitMQ
│   ├── src/            # Código-fonte do consumidor
│   ├── package.json    # Dependências do projeto
│   └── tsconfig.json   # Configuração do TypeScript
│
└── README.md           # Documentação geral do projeto
```

<h1 id="techs">Técnologias Usadas</h1>

### Producer
  
  ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
  
  ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
  
  ![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
  
  ![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
  
### Database
  
  ![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)

### System Broker

  ![RabbitMQ](https://img.shields.io/badge/Rabbitmq-FF6600?style=for-the-badge&logo=rabbitmq&logoColor=white)

### Consumer

  ![TypeScript](https://img.shields.io/badge/typescript-%23007ACC.svg?style=for-the-badge&logo=typescript&logoColor=white)

  ![NodeJS](https://img.shields.io/badge/node.js-6DA55F?style=for-the-badge&logo=node.js&logoColor=white)

  ![NPM](https://img.shields.io/badge/NPM-%23CB3837.svg?style=for-the-badge&logo=npm&logoColor=white)

<h1 id="execute">🚀 Executar o Projeto</h1>
<p>De preferência abra 4 terminais, um para o RabbitMQ, um para o Postgres, um para o Producer e o outro para o Consumer</p>
Pré-requisitos: Java 17, Maven e Node >= 18

### Clonar repositório
```bash
git clone https://github.com/Joao-Darwin/investment-queue-system
```
### Entrar na pasta do projeto
```bash
cd investment-queue-system
```

<h2>Middleware (RabbitMQ)</h2>

### Entrar na pasta do projeto
```bash
cd middleware
```
### Subir container do RabbitMQ
```bash
docker compose up
```

<h2>Producer (Java + SpringBoot)</h2>

### Entrar na pasta do projeto
```bash
cd producer
```
### Subir container do Postgres
```bash
docker compose up producer_postgres
```
### Instalar o Maven (caso não possua)
```bash
sudo apt-get install maven
```
### Instalar depedências do projeto
```bash
sudo mvn clean install
```
### Executar o projeto
```bash
sudo mvn spring-boot:run
```
<h2>Consumer (Typescript + NodeJS)</h2>

### Entrar na pasta do projeto
```bash
cd consumer
```
### Renomear arquivo com ENVs
```bash
cp .env.example .env
```
### Adicionar ENVs
```bash
DATABASE_HOST=localhost
DATABASE_PORT=5432
DATABASE_USER=postgres
DATABASE_PASSWORD=postgres
DATABASE_NAME=producer

RABBITMQ_URL=amqp://admin:admin@localhost:5672
QUEUE_NAME=invest_queue
```
### Instalar depedências
```bash
npm i
```
### Executar o Consumer
```bash
npm start
```
Após execução de todos os passos teremos:
- Producer rodando na porta `8080`
- Banco de Dados rodando na porta `5432`
- Middleware rodando na porta `5672`
- Consumer escutando o middleware

## Vídeo demonstrativo
<a target="_blank" href="https://youtu.be/CW7PwLC_hZA">Youtube</a>
