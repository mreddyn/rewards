FROM openjdk:22-jdk-slim

WORKDIR /app

# copy the jar to the working directory
COPY target/rewards-0.0.1-SNAPSHOT.jar /app/receipt-processor.jar

EXPOSE 8080

# run the application
ENTRYPOINT ["java", "-jar", "receipt-processor.jar"]
