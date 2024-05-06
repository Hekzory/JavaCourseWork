FROM eclipse-temurin:21-alpine AS build

COPY src/main ./src/main
COPY --chmod=765 gradlew build.gradle settings.gradle ./
COPY gradle ./gradle

RUN ./gradlew --no-daemon --parallel -Porg.gradle.caching=false --no-build-cache --no-configuration-cache clean bootJar

FROM eclipse-temurin:21-jre-alpine as run

# Set the working directory inside the container
WORKDIR /opt/app

COPY --from=build /build/libs/medclinic.jar app.jar

# Specify the command to run the application
ENTRYPOINT ["java","-jar","/opt/app/app.jar"]
