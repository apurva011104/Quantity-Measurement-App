# Use Maven + Java image (no mvnw needed)
FROM maven:3.9.9-eclipse-temurin-21

WORKDIR /app

COPY . .

# Build project
RUN mvn clean package -DskipTests

# Run app
CMD ["java", "-jar", "target/*.jar"]