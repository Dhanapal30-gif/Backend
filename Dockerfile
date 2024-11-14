# Stage 1: Build the application
FROM openjdk:21-jdk-slim as build

# Set the working directory
WORKDIR /app

# Install Maven (since it's not included in the slim image)
RUN apt-get update && apt-get install -y maven

# Copy the project files into the container
COPY . .

# Run Maven to build the project (this will create the JAR file in the target directory)
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:21

# Copy the JAR file from the build stage
COPY --from=build /app/target/orkaTraks-0.0.1-SNAPSHOT.jar /app/orkaTraks.jar

# Expose the port the app will run on
EXPOSE 8080

# Set the entry point to run the JAR file
ENTRYPOINT ["java", "-jar", "/app/orkaTraks.jar"]
