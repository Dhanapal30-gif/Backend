# Use official JDK 21 base image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built .jar file from your local machine to the container
COPY target/orkaTraks-0.0.1-SNAPSHOT.jar /app/orkaTraks.jar

# Expose the port that Spring Boot runs on (default is 8080)
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "orkaTraks.jar"]
