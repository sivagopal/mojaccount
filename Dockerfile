FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} account.jar
ENTRYPOINT ["java", "-jar", "/account.jar"]