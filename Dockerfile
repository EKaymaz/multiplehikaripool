FROM openjdk:8-jre-alpine
MAINTAINER Erdem Kaymaz erdemkaymaz@gmail.com
COPY target/*.jar /app.jar
EXPOSE 8080
CMD ["/usr/bin/java", "-jar", "/app.jar"]