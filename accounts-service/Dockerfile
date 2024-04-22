FROM gradle:8.7.0-jdk17 AS build
WORKDIR /home/gradle/src
COPY --chown=gradle:gradle . .
RUN gradle build -x test

FROM openjdk:17-alpine
EXPOSE 8080
WORKDIR /app
COPY --from=build /home/gradle/src/build/libs/accounts-service-0.0.1-SNAPSHOT.jar ./
COPY postgresql-42.7.3.jar /app/
ENV CLASSPATH=/app/postgresql-42.7.3.jar:$CLASSPATH

CMD ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom",  "-jar", "accounts-service.jar"]