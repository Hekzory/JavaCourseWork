FROM eclipse-temurin:21-alpine AS build

COPY gradle gradle/
COPY --chmod=765 gradlew build.gradle settings.gradle ./
COPY src/main ./src/main

RUN --mount=type=cache,target=/root/.gradle ./gradlew --build-cache --parallel --no-daemon clean bootJar


FROM eclipse-temurin:21-jre-alpine as run

# Set the working directory inside the container
WORKDIR /opt/app

COPY --from=build /build/libs/medclinic.jar app.jar

# Specify the command to run the application
ENTRYPOINT ["java","-jar","/opt/app/app.jar"]
