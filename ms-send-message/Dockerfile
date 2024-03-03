FROM maven:3.9.3-eclipse-temurin-17-alpine as microservices
WORKDIR /app

# Copia todos los archivos al directorio de trabajo
COPY . /app

# Compila el proyecto y empaqueta en un JAR
RUN mvn clean package -DskipTests

ENTRYPOINT ["sh", "-c", "java -jar $(find /app/target -name '*.jar' -print -quit)"]
