# 1. Verwende ein Java-Laufzeit-Image
FROM eclipse-temurin:17-jdk-jammy AS build

# 2. Setze Arbeitsverzeichnis
WORKDIR /app

# 3. Kopiere Projektdateien ins Image
COPY . .

# 4. Baue die Anwendung mit Gradle
RUN ./gradlew clean build -x test

# 5. Erstelle ein minimales Laufzeit-Image
FROM eclipse-temurin:17-jre-jammy

# 6. Arbeitsverzeichnis für die App
WORKDIR /app

# 7. Kopiere die JAR-Datei aus dem Build-Image
COPY --from=build /app/build/libs/*.jar app.jar

# 8. Definiere den Startbefehl
ENTRYPOINT ["java", "-jar", "app.jar"]
