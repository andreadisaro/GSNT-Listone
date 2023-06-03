FROM maven:3.8.3-adoptopenjdk-15 AS build

RUN mkdir /project

COPY . /project

WORKDIR /project

RUN mvn clean package

FROM adoptopenjdk/openjdk15:jre-15.0.2_7-alpine

COPY --from=build /project/backend/target/listone-backend-1.jar /app/app.jar

EXPOSE 8089

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app/app.jar ${0} ${@}"]