FROM openjdk:17-jdk-slim
COPY ./build/libs/prac7-0.0.1-SNAPSHOT.war app.war
EXPOSE 8000
CMD ["java", "-jar", "app.war", "--server.port=8000"]