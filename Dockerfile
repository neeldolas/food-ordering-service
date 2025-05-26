# Use official lightweight OpenJDK 21 image
FROM eclipse-temurin:21-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy your built jar into the container
COPY target/user-service-0.0.1-SNAPSHOT.jar app.jar

# Expose port your app runs on
EXPOSE 8081

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
