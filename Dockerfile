# Use a base image with JDK 21
FROM eclipse-temurin:21-jdk

# Set the working directory
WORKDIR /app

# Copy the JAR file into the Docker image
COPY build/libs/coffee-shop.jar app.jar

# Expose the application port
EXPOSE 8081

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
