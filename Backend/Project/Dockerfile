FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY Backend/Project/pom.xml ./pom.xml
COPY Backend/Project/src ./src
RUN mvn clean package

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/Project-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
