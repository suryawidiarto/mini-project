FROM maven AS build

WORKDIR /app

COPY pom.xml /app

RUN mvn dependency:resolve

COPY . /app

RUN mvn clean

RUN mvn package -DskipTests

FROM amazoncorretto:17-alpine

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]