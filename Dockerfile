FROM gradle:8.7-jdk-21-and-22-alpine AS builder
COPY . .
RUN ./gradlew bootJar --no-daemon
RUN pwd

FROM openjdk:21-jdk-slim
EXPOSE 8080
COPY --from=builder /home/gradle/build/libs/university-0.0.1-SNAPSHOT.jar university.jar

ENTRYPOINT ["java","-jar","university.jar"]