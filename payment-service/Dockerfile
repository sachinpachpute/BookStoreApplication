FROM openjdk:17-jdk-slim

VOLUME /tmp

ARG JAR_FILE

COPY target/${JAR_FILE} payment-service.jar

#Using Dokerize to check whether db is up, if it is then start this service.
#COPY dockerize dockerize

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/payment-service.jar"]
