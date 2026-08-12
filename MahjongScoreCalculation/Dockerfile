# Build Stage
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# ★ mvnw に実行権限を付与（必須）
RUN chmod +x mvnw

COPY . .
RUN ./mvnw clean package -DskipTests


# Run Stage
FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=build /app/target/MahjongScoreCalculation-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

