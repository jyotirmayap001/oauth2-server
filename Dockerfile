FROM openjdk:17-jdk-alpine
COPY target/oauth2-server-0.0.1.jar oauth2-server.jar
ENTRYPOINT ["java","-jar","/oauth2-server.jar"]