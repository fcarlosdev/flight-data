FROM openjdk:17
COPY target/flightdata-0.0.1-SNAPSHOT.jar flight-api-1.0.0.jar
ENTRYPOINT ["java", "-jar", "flight-api-1.0.0.jar"]
