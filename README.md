# Consulta CEP API

## Descrição

A **Consulta CEP API** é uma aplicação que permite a consulta de endereços a partir de um CEP fornecido. A aplicação realiza a busca em uma API externa e registra os logs das consultas em um banco de dados, incluindo o horário da consulta e os dados retornados pela API. A aplicação segue os princípios básicos de SOLID para garantir um código limpo e de fácil manutenção.

## Desenho Técnico

### Arquitetura da Solução
![Arquitetura da Solução](https://github.com/user-attachments/assets/1a60dc43-7d52-41a6-bbff-3700c301a151)

#### Legenda
- ![#ffffcc](https://via.placeholder.com/15/ffffcc/000000?text=+) Fluxo consulta de CEP
- ![#b9e0a5](https://via.placeholder.com/15/b9e0a5/000000?text=+) Fluxo consulta de Logs
- ![#f19c99](https://via.placeholder.com/15/f19c99/000000?text=+) Compartilhado entre os dois fluxos
  
*Observação: o `LoggingFilter` só irá salvar logs do fluxo de consulta de CEP*

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
4. **Consulta de Logs**: A aplicação permite consultar os logs das consultas de CEP, incluindo o horário da consulta e os dados retornados pela API.

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

## Instruções para Configuração do Wiremock

### Cenários Criados

1. **Cenário de Sucesso**: Qualquer CEP no formato `00000-000` a `99999-998` retornará uma resposta de sucesso.
2. **Cenário de Erro**: Se o usuário digitar o CEP `99999-999`, será simulada uma resposta de erro.

### Configuração do Wiremock

#### Arquivo `wiremock/mappings/cep-mapping.json`

```json
{
  "mappings": [
    {
      "request": {
        "method": "GET",
        "urlPathPattern": "/cep/.*"
      },
      "response": {
        "status": 200,
        "bodyFileName": "cep-sucesso-response.json",
        "headers": {
          "Content-Type": "application/json"
        }
      }
    },
    {
      "request": {
        "method": "GET",
        "urlPathPattern": "/cep/99999-999"
      },
      "response": {
        "status": 404,
        "bodyFileName": "cep-erro-response.json",
        "headers": {
          "Content-Type": "application/json"
        }
      }
    }
  ]
}
```

#### Arquivo `wiremock/__files/cep-sucesso-response.json`

```json
{
  "cep": "01001-000",
  "logradouro": "Praça da Sé",
  "numero": "11",
  "complemento": "lado ímpar",
  "bairro": "Sé",
  "cidade": "São Paulo",
  "estado": "SP"
}
```

#### Arquivo `wiremock/__files/cep-erro-response.json`

```json
{
  "statusCode": 404,
  "message": "Endereço não encontrado para o CEP fornecido"
}
```

### Executando o Wiremock

Para executar o Wiremock com essas configurações, certifique-se de que os arquivos estão no diretório correto e inicie o Wiremock. A aplicação estará pronta para simular os cenários de sucesso e erro conforme descrito.

## Executando a Aplicação

1. Clone o repositório.
2. Configure o banco de dados PostgreSQL e o Wiremock utilizando o `docker-compose.yml`.
3. Execute a aplicação utilizando o Maven:
   ```sh
   mvn spring-boot:run
   ```
4. Acesse a documentação da API em `http://localhost:8081/swagger-ui.html`.
