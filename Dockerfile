# Etapa 1: Construcción (Build)
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copiamos primero el pom.xml para aprovechar la caché de capas de Docker en las dependencias
COPY pom.xml .
# Descargamos las dependencias antes de copiar el código fuente
RUN mvn dependency:go-offline -B

# Copiamos el código fuente y compilamos
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución (Run)
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copiamos el .jar generado de la etapa anterior
COPY --from=build /app/target/inventarios-0.0.1-SNAPSHOT.jar app.jar

# Exponemos el puerto
EXPOSE 8080

# Punto de entrada
ENTRYPOINT ["java", "-jar", "app.jar"]
