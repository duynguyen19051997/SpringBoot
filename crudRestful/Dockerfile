# For Java 8, try this
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="nguyenvuanhduy1905@gmail.com"

WORKDIR /app/

EXPOSE 3000

# Refer to Maven build -> finalName
ARG JAR_FILE=target/crudrestful-0.0.1-SNAPSHOT.jar

# cp target/spring-boot-web.jar /opt/app/app.jar
ADD ${JAR_FILE} crud-restful.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","crud-restful.jar"]
