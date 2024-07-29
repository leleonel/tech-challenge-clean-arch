# Desafio Tech Challenge FIAP

Este projeto foi desenvolvido para atender a solução de
uma lanchonete que faz pedidos por meio do autoatendimento. 


## Índice

- [Introdução](#introdução)
- [Pré-requisitos](#pré-requisitos)
- [Instalação](#instalação)
- [Configuração](#configuração)
- [Executando a Aplicação](#executando-a-aplicação)
- [Documentação da API](#documentação-da-api)
- [Testes](#testes)
- [Contribuição](#contribuição)
- [Licença](#licença)
- [Contato](#contato)

## Introdução

Este projeto é uma aplicação Spring Boot que fornece uma API RESTful para gerenciar o autoatendimento para pedidos de uma lanchonete.
A aplicação inclui uma documentação Swagger gerada automaticamente usando o Springdoc OpenAPI.

## Pré-requisitos

Antes de começar, certifique-se de ter os seguintes softwares instalados:

- [Java 17+](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- [Maven 3.6+](https://maven.apache.org/download.cgi)
- [Git](https://git-scm.com/downloads)
- [Docker](https://www.docker.com/products/docker-desktop)
- [Docker Compose](https://docs.docker.com/compose/install/)

## Instalação

Clone o repositório para o seu ambiente local:

```sh
git clone https://github.com/leleonel/tech-challenge.git
cd tech-challenge
```

Configuração
Configure as propriedades do banco de dados em src/main/resources/application.properties ou src/main/resources/application.yml:

## application.properties

spring.datasource.url=${SPRING_DATASOURCE_URL}
spring.datasource.username=${SPRING_DATASOURCE_USERNAME}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}

springdoc.api-docs.enabled=true
springdoc.swagger-ui.enabled=true


Certifique-se de substituir seu_banco_de_dados, seu_usuario e sua_senha pelas suas credenciais reais.

## Executando a Aplicação
Você pode executar a aplicação usando o Maven:

```sh
mvn spring-boot:run
```

## Ou você pode construir o JAR e executá-lo:

```sh
mvn clean package
java -jar target/tech-challenge-0.0.1-SNAPSHOT.jar
```

## Documentação da API
A documentação da API gerada pelo Swagger pode ser acessada em:

```sh
http://localhost:8080/swagger-ui.html
```

## Testes
Para executar os testes unitários, utilize o comando:

```sh
mvn clean test
```

## Executando com Docker
# Construir e Executar os Containers
Para construir e executar os containers Docker, use o Docker Compose:

```sh
docker-compose up --build
```
# Parar os Containers
Para parar os containers, use o comando:

```sh
docker-compose down
```

## Contribuição
Se você deseja contribuir para este projeto, por favor siga os passos abaixo:

Faça um fork do repositório
Crie uma branch para sua feature (git checkout -b feature/nova-feature)
Commit suas mudanças (git commit -m 'Adiciona nova feature')
Faça o push para a branch (git push origin feature/nova-feature)
Abra um Pull Request


## Licença
Contato
Nome - @leleonel - leticiamoreiraleonel@gmail.com

Link do Projeto: https://github.com/leleonel/tech-challenge.git


