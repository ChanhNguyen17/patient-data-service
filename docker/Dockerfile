# Use the base image with Java 17
FROM registry.opensource.zalan.do/library/eclipse-temurin-17-jdk:latest

# Set the maintainer label
LABEL maintainer="Chanh Nguyen <chanhnguyen171@gmail.com>"

# Copy the application JAR file
COPY build/libs/patient-data-service-0.0.1.jar /app.jar

# Set the entrypoint command to start the application
ENTRYPOINT exec java $JAVA_OPTS -jar /app.jar
