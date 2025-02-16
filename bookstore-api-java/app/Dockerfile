FROM openjdk:21-oraclelinux8 AS develop
RUN mkdir ./app
COPY . ./app
WORKDIR ./app
RUN ./gradlew build && java -jar build/libs/bookstore-api-java-0.0.1-SNAPSHOT.jar

FROM openjdk:21-oraclelinux8 AS runtime
RUN mkdir ./app
COPY --from=develop . ./app
ENTRYPOINT ["java","-jar","/app.jar"]