FROM eclipse-temurin:17-jdk

WORKDIR /app

ARG JAR_FILE

COPY products/target/${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]