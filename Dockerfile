FROM openjdk:8
COPY target/Patient-0.0.1-SNAPSHOT.jar Patient-0.0.1-SNAPSHOT.jar
EXPOSE 9091
ENTRYPOINT ["java", "-jar", "Patient-0.0.1-SNAPSHOT.jar"]