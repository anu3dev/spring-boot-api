# Step 1: Use a lightweight JDK image
FROM eclipse-temurin:20-jdk-alpine

# Step 2: Set working directory
WORKDIR /app

# Step 3: Copy Maven build jar into image
COPY target/anu3dev-0.0.1-SNAPSHOT.jar app.jar

# Step 4: Run jar
ENTRYPOINT ["java", "-jar", "app.jar"]