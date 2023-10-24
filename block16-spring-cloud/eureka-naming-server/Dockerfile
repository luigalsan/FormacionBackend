FROM openjdk:17-jdk-slim
COPY target/*.jar /eureka-naming-server-0.0.1-SNAPSHOT.jar
EXPOSE 8761
CMD ["java", "-jar", "/eureka-naming-server-0.0.1-SNAPSHOT.jar"]