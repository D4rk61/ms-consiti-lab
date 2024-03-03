FROM openjdk:17 as single
COPY "./target/apiCompose-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]


# Perfil agregado
FROM maven:3.9.3-eclipse-temurin-17-alpine as microservices
WORKDIR /app

# Copia todos los archivos al directorio de trabajo
COPY . /app

# Compila el proyecto y empaqueta en un JAR
RUN mvn clean package -DskipTests

ENTRYPOINT ["sh", "-c", "java -jar $(find /app/target -name '*.jar' -print -quit)"]
