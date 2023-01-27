FROM maven AS build

WORKDIR /app

COPY pom.xml /app

RUN mvn dependency:resolve

COPY . /app

RUN mvn clean

FROM amazoncorretto:17-alpine

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]