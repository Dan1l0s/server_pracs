FROM openjdk:17-jdk-slim
COPY ./build/libs/prac5-0.0.1-SNAPSHOT.war app.war
EXPOSE 8080
CMD ["java", "-jar", "app.war", "--server.port=8080"]