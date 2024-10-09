FROM alpine/java21-jdk as developerImage
RUN "mkdir ./app"
COPY . ./app
WORKDIR ./app
RUN "./gradlew build && java -jar build/libs/gs-spring-boot-docker-0.1.0.jar"

FROM alpine/java21-sdk as runtimeImage
RUN "mkdir ./app"
COPY --from=develop . ./app
ENTRYPOINT ["java","-jar","/app.jar"]