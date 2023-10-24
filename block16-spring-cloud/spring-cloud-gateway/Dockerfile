FROM openjdk:17-jdk-slim
COPY target/*.jar /spring-cloud-gateway-0.0.1-SNAPSHOT.jar
EXPOSE 8083
CMD ["java", "-jar", "/spring-cloud-gateway-0.0.1-SNAPSHOT.jar"]