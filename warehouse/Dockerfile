# Build stage
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /home/app/warehouse
COPY /warehouse/src /home/app/warehouse/src
COPY /warehouse/pom.xml /home/app/warehouse
COPY pom.xml /home/app
RUN mvn clean test package

# Package stage
FROM openjdk:21-slim
COPY --from=build /home/app/warehouse/target/*.jar app.jar
ENV WAREHOUSE_UDP_HOST=0.0.0.0
EXPOSE 3344/udp
EXPOSE 3355/udp
ENTRYPOINT ["java","-jar","app.jar"]