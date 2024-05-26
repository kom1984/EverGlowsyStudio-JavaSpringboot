# Use Maven image to build the project
FROM maven:3.8.6 AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and resolve dependencies
COPY pom.xml /app
RUN mvn dependency:resolve

# Copy the rest of the project and build the application
COPY . /app
RUN mvn clean package -DskipTests

# Use OpenJDK image to run the application
FROM openjdk:21-jdk

# Expose the application port
EXPOSE 8080

# Argument for the JAR file
ARG JAR_FILE=target/springboot-everglowsystudio.jar

# Add the JAR file to the container
ADD ${JAR_FILE} app.jar

# Set the entry point to run the JAR file
ENTRYPOINT ["java", "-jar", "/app.jar"]
