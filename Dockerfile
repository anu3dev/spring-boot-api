#############################################################
# STEP 1: Build the application using Maven + JDK 21
#############################################################
# Use an official Maven image that already has JDK 21 installed
FROM maven:3.9.6-eclipse-temurin-21 AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy everything from the current folder (project) to /app in the container
COPY . .

# Run Maven inside the container to build the project
# - 'clean' removes previous builds
# - 'package' compiles the project and creates a JAR inside /app/target/
# - '-DskipTests' avoids running tests during the build (faster build)
RUN mvn clean package -DskipTests


#############################################################
# STEP 2: Create a smaller runtime image with only JDK 21
#############################################################
# Use a lightweight JDK 21 image (based on Alpine Linux)
FROM eclipse-temurin:21-jdk-alpine

# Set the working directory inside the runtime container
WORKDIR /app

# Copy the built JAR from the "builder" stage into this final image
# Rename it to app.jar inside /app
COPY --from=builder /app/target/anu3dev-0.0.1-SNAPSHOT.jar app.jar

# Define the startup command when the container runs
# This will launch your Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]