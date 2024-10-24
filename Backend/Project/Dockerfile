FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copy Maven files from the correct paths
COPY ./pom.xml ./pom.xml
COPY ./src ./src

# Build the project using Maven
RUN mvn clean package

FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the built JAR to the final image
COPY --from=build /app/target/Project-0.0.1-SNAPSHOT.jar app.jar

# Expose the required port
EXPOSE 8080

# Start the application
ENTRYPOINT ["java", "-jar", "app.jar"]
