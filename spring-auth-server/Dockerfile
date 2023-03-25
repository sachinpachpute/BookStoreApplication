FROM openjdk:17-jdk-slim

VOLUME /tmp

ARG JAR_FILE

COPY target/${JAR_FILE} spring-auth-server.jar

#COPY ./src/main/resources/templates /templates

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/spring-auth-server.jar"]
