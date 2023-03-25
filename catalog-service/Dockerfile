FROM openjdk:17-jdk-slim

VOLUME /tmp

ARG JAR_FILE

COPY target/${JAR_FILE} catalog-service.jar

COPY ./images /images

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/catalog-service.jar"]