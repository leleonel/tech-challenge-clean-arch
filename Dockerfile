# Use uma imagem base do JDK 17
FROM openjdk:17-jdk-slim

# Adicione um volume apontando para /tmp
VOLUME /tmp

# Argumento para o arquivo JAR
ARG JAR_FILE=target/tech-challenge-0.0.1-SNAPSHOT.jar

# Copie o arquivo JAR para o container
COPY ${JAR_FILE} app.jar

# Exponha a porta que a aplicação vai rodar
EXPOSE 8081

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "/app.jar"]
