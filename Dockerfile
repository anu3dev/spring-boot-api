# Step 1: Build with Maven
FROM maven:3.9.6-eclipse-temurin-20-alpine AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Step 2: Run with JDK
FROM eclipse-temurin:20-jdk-alpine
WORKDIR /app
COPY --from=builder /app/target/anu3dev-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]