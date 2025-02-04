# OpenJDK 21 istifadə edirik
FROM openjdk:21-jdk-slim

# İş qovluğunu təyin edirik
WORKDIR /app

# JAR faylını konteynerə əlavə edirik
COPY build/libs/*.jar app.jar

# Spring Boot tətbiqini başladırıq
ENTRYPOINT ["java", "-jar", "app.jar"]
