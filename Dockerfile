FROM maven:3.8.6-openjdk-18-slim AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file
COPY --from=build  app/target/rewards-0.0.1-SNAPSHOT.jar /app/receipt-processor.jar

EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "receipt-processor.jar"]
