FROM openjdk:11.0.4-jre-slim
ENV APPLICATION_PORT 9080
COPY target/to-do-tasks-app-*.jar to-do-tasks-app.jar
CMD java -jar  -Dspring.profiles.active=docker -Dserver.port=${APPLICATION_PORT} to-do-tasks-app.jar
EXPOSE ${APPLICATION_PORT}