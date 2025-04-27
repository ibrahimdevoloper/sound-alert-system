# Use a lightweight Maven image for building
FROM eclipse-temurin:21-jdk-alpine AS build

# Set the working directory
WORKDIR /app

# Copy only necessary files for dependency resolution
COPY .mvn/ ./.mvn
COPY mvnw pom.xml ./
RUN chmod +x ./mvnw
RUN ./mvnw dependency:go-offline

EXPOSE 5672

# Copy the rest of the project files
COPY src ./src

CMD ["./mvnw", "spring-boot:run"]