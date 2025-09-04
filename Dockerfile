# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom.xml trước để cache dependency
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code và build
COPY src src
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# Copy file jar từ stage build
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
