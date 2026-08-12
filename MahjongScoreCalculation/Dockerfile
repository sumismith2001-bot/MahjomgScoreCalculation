# Build Stage
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# まずコピーする（重要）
COPY . .

# mvnw に実行権限を付与
RUN chmod +x mvnw

# Maven ビルド
RUN ./mvnw clean package -DskipTests

# Run Stage
FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/target/MahjongScoreCalculation-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
