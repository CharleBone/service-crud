ARG NAME=service-crud

FROM openjdk:17-jdk-alpine as builder

ARG NAME

WORKDIR /app/

COPY ./pom.xml /app
COPY ./.mvn ./.mvn
COPY ./mvnw .
COPY ./pom.xml .

RUN chmod +x ./mvnw

#RUN  ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
RUN ./mvnw dependency:go-offline
COPY ./src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

ARG NAME

WORKDIR /app

RUN  mkdir ./logs

ARG TARGET_FOLDER=/app/target
COPY --from=builder $TARGET_FOLDER/service-crud-0.0.1-SNAPSHOT.jar .
ARG PORT_APP=8001
ENV PORT $PORT_APP
EXPOSE $PORT

CMD ["java", "-jar", "service-crud-0.0.1-SNAPSHOT.jar"]