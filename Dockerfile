FROM adoptopenjdk/openjdk11:latest
RUN mkdir /opt/app
COPY target/urap-0.0.1-SNAPSHOT.jar /opt/app

EXPOSE 8080
CMD ["java", "-jar", "/opt/app/urap-0.0.1-SNAPSHOT.jar"]