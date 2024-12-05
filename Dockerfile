# Use an official OpenJDK base image
FROM eclipse-temurin:21-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the WAR file into the Docker image
COPY target/maokimhuong-web-api-0.0.1-SNAPSHOT.war /app/maokimhuong-web-api.war

# Expose the port on which the app will run
EXPOSE 8080

# Command to run the Spring Boot WAR file
ENTRYPOINT ["java", "-jar", "/app/maokimhuong-web-api.war"]
