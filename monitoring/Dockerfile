# Build stage
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /home/app/monitoring
COPY /monitoring/src /home/app/monitoring/src
COPY /monitoring/pom.xml /home/app/monitoring
COPY pom.xml /home/app
RUN mvn clean test package

# Package stage
FROM openjdk:21-slim
COPY --from=build /home/app/monitoring/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]