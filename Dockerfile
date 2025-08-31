FROM eclipse-temurin:17-jdk-alpine

# Set work directory
WORKDIR /app

# Copy the jar file (update the name if needed)
COPY target/*.jar app.jar

# Expose the port used by your Spring Boot app
EXPOSE 5173

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
