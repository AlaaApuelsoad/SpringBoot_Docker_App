# Use official OpenJDK image
FROM openjdk:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the JAR file from the target folder into the container
COPY target/DockerApp-0.0.1-SNAPSHOT.jar DockerApp-0.0.1-SNAPSHOT.jar

# Expose the application port
EXPOSE 80

# Run the application
ENTRYPOINT ["java", "-jar", "DockerApp-0.0.1-SNAPSHOT.jar"]
