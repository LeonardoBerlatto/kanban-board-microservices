FROM openjdk:17-jdk-alpine

EXPOSE 8080

ARG SERVICE_NAME

ARG JAR_FILE=./${SERVICE_NAME}/target/app.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar","--spring.profiles.active=docker"]