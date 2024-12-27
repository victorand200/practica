FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/sale-project-0.0.1-SNAPSHOT.jar sale-project.jar

ENTRYPOINT ["java", "-jar", "sale-project.jar"]

EXPOSE 9898