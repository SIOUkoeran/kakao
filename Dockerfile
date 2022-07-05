FROM  azul/zulu-openjdk:11
LABEL maintainer="alstnalsgud@gmail.com"
EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]