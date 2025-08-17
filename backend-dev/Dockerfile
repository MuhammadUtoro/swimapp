# Stage 1: Build (optional, since you already have the jar)
# FROM maven:3.9.2-eclipse-temurin-17 AS build
# WORKDIR /app
# COPY . .
# RUN ./mvnw package -Dquarkus.package.type=uber-jar

# Stage 2: Run
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the already built uber-jar from your backend target folder

COPY target/swimapp-1.0.0-SNAPSHOT-runner.jar app.jar


# Expose Quarkus default port
EXPOSE 8081

# Run the uber-jar
ENTRYPOINT ["java","-jar","app.jar"]
