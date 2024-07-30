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
### Construir e Executar os Containers
Para construir e executar os containers Docker, use o Docker Compose:

```sh
docker-compose up --build
```
# Parar os Containers
Para parar os containers, use o comando:

```sh
docker-compose down
```


## Executando com Kubernetes
### Para executar a aplicação no Kubernetes usando Docker Desktop, siga os passos abaixo:

#### Requisitos
- kubectl
- Docker Desktop com Kubernetes habilitado

#### Habilitar Kubernetes no Docker Desktop
- Abra o Docker Desktop.
- Vá até as configurações (ícone de engrenagem).
- No menu à esquerda, selecione "Kubernetes".
- Marque a opção "Enable Kubernetes".
- Clique em "Apply & Restart".


#### Deploy no Kubernetes

Certifique-se de que o Kubernetes está rodando no Docker Desktop. Você pode verificar isso com o seguinte comando:

```sh
kubectl cluster-info
```

Aplique os manifests do Kubernetes:

```sh
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml
kubectl apply -f k8s/hpa.yaml
```

#### Verificar os Recursos

Verifique se os recursos foram criados corretamente:

```sh
kubectl get deployments
kubectl get services
kubectl get hpa
```

# Executando a Aplicação Localmente no Kubernetes no Google Cloud

## Pré-requisitos

- Conta no Google Cloud Platform (GCP)
- gcloud CLI instalado e configurado
- kubectl instalado
- Docker instalado
- Acesso ao projeto tech-challenge-clean-arch no GitHub

## Passo a Passo

### 1. Configuração Inicial

#### 1.1. Autentique-se no Google Cloud

```bash
gcloud auth login

#### Acessar a Aplicação
Obtenha o URL para acessar a aplicação:

Exporte as portas para o host local usando o comando abaixo, onde 30001 é a porta configurada no serviço:

```sh
kubectl port-forward service/tech-challenge-clean-arch-service 8080:30001

```

#### 1.2. Configure o projeto
Substitua your-project-id pelo ID do seu projeto no Google Cloud.

```bash
gcloud config set project your-project-id
```

#### 1.3. Configure a região e zona
Substitua us-west1-a pela região e zona do seu cluster.

```bash
gcloud config set compute/zone us-west1-a
```

#### 2. Criar um Cluster Kubernetes
   ```bash
   gcloud container clusters create tech-challenge-cluster --num-nodes=3
   ```
#### 2.2. Conecte-se ao cluster
  ```bash
   gcloud container clusters get-credentials tech-challenge-cluster
   ```
#### 3. Configurar a Aplicação
   3.1. Construa a imagem Docker da aplicação
   Navegue até o diretório da aplicação e construa a imagem Docker.

```bash
docker build -t gcr.io/your-project-id/tech-challenge-app:v1 .
```
#### 3.2. Envie a imagem para o Container Registry
```bash
docker push gcr.io/your-project-id/tech-challenge-app:v1
```
#### 4. Deploy no Kubernetes
   4.1. Crie os arquivos de manifesto Kubernetes
   Crie um arquivo deployment.yaml e service.yaml com a configuração do seu deployment e service e faça o deployment
   detas configurações: 
    
   ```bash
   kubectl apply -f deployment.yaml
   kubectl apply -f service.yaml
   kubectl apply -f hpa.yaml
   kubectl apply -f mysql-deployment.yaml
   ```

#### 7. Monitorar o Cluster
   Use os comandos abaixo para monitorar o cluster e os recursos:

```bash
kubectl get pods
kubectl get deployments
kubectl get services
kubectl top pods
kubectl top nodes
```

Agora você pode acessar a aplicação através do IP indicado pelo Kubernets.

## Mantendo a Segunda Aplicação (Webhook) Ativa
Para garantir o funcionamento completo da solução, é necessário manter a segunda aplicação que simula o webhook ativa,
github: 

Certifique-se de que a segunda aplicação que simula o webhook está rodando. Esta aplicação é um serviço externo e não faz parte do cluster Kubernetes.
Verifique a documentação da segunda aplicação para obter as instruções específicas de execução.


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


