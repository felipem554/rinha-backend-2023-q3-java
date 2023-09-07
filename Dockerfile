FROM openjdk:20-jdk-slim as BUILD

WORKDIR /app

COPY src /app/src
COPY pom.xml /app
COPY .mvn /app/.mvn
COPY mvnw /app/mvnw

RUN ./mvnw clean package -DskipTests

FROM openjdk:20-jdk-slim as RUNTIME

COPY --from=BUILD /app/target/rinhabackend2023-0.0.1-SNAPSHOT.jar /rinha.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-XX:+UseParallelGC", "-XX:MaxRAMPercentage=75", "-jar", "./rinha.jar" ]
