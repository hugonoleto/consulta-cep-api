# Consulta CEP API

## Descrição

A **Consulta CEP API** é uma aplicação que permite a consulta de endereços a partir de um CEP fornecido. A aplicação realiza a busca em uma API externa e registra os logs das consultas em um banco de dados, incluindo o horário da consulta e os dados retornados pela API. A aplicação segue os princípios básicos de SOLID para garantir um código limpo e de fácil manutenção.

## Desenho Técnico

### Arquitetura da Solução
![Arquitetura da Solução](https://github.com/user-attachments/assets/5d50264e-a082-46b6-9da8-0c1ebcac1cb7)

### Componentes Principais

1. **Controller**: Responsável por receber as requisições HTTP e retornar as respostas apropriadas.
2. **Service**: Contém a lógica de negócios, incluindo a consulta de CEP e o registro de logs.
3. **Client**: Utiliza o Feign para realizar chamadas à API externa de consulta de CEP.
4. **Repository**: Gerencia as operações de persistência dos logs no banco de dados.
5. **Filter**: Intercepta as requisições e respostas HTTP para registrar os logs.
6. **Aspect**: Valida o formato do CEP antes de realizar a consulta.

## Funcionalidades

1. **Consulta de CEP**: A aplicação permite consultar endereços a partir de um CEP fornecido.
2. **Registro de Logs**: As consultas são registradas em um banco de dados, incluindo o horário da consulta e os dados retornados pela API.
3. **Validação de CEP**: O formato do CEP é validado antes de realizar a consulta.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.4.1**
- **Spring Data JPA**
- **Spring Cloud OpenFeign**
- **PostgreSQL**
- **Wiremock**
- **Lombok**
- **MapStruct**
- **Swagger/OpenAPI**
- **Docker**

## Estrutura do Projeto

```plaintext
src
├── main
│   ├── java
│   │   └── com
│   │       └── f1rst
│   │           └── desafio
│   │               └── consulta
│   │                   └── cep
│   │                       ├── api
│   │                       │   ├── aspect
│   │                       │   ├── client
│   │                       │   ├── config
│   │                       │   ├── controller
│   │                       │   ├── domain
│   │                       │   │   ├── dto
│   │                       │   │   └── entity
│   │                       │   ├── mapper
│   │                       │   ├── repository
│   │                       │   └── service
│   │                       └── Application.java
│   └── resources
│       ├── application.properties
│       └── db
│           └── migration
│               └── V1.0__inicial.sql
└── test
    └── java
        └── com
            └── f1rst
                └── desafio
                    └── consulta
                        └── cep
                            └── api
                                └── ApplicationTests.java
```

## Configuração

### Banco de Dados

A aplicação utiliza PostgreSQL como banco de dados. A configuração do banco de dados pode ser encontrada no arquivo `application.properties`.

### Docker

O projeto inclui um arquivo `docker-compose.yml` para facilitar a configuração e execução dos serviços necessários, como o banco de dados PostgreSQL e o Wiremock para simulação da API externa.

### Swagger

A documentação da API está disponível através do Swagger, acessível em `/swagger-ui.html`.

## Executando a Aplicação

1. Clone o repositório.
2. Configure o banco de dados PostgreSQL e o Wiremock utilizando o `docker-compose.yml`.
3. Execute a aplicação utilizando o Maven:
   ```sh
   mvn spring-boot:run
   ```
4. Acesse a documentação da API em `http://localhost:8081/swagger-ui.html`.
