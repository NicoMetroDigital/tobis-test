plugins {
    id("org.springframework.boot") version "2.7.5" // oder neuere Version
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")      // Web-API-Unterstützung
    implementation("com.h2database:h2")                                     // H2-Datenbank für lokale Tests
    implementation("org.jetbrains.kotlin:kotlin-reflect")                   // Kotlin Reflection
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")               // Kotlin Standardbibliothek
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")     // Jakarta Persistence API
    implementation("javax.persistence:javax.persistence-api:2.2")           // Optional, falls benötigt
    testImplementation("org.springframework.boot:spring-boot-starter-test") // Test-Abhängigkeiten
}

tasks.withType<org.springframework.boot.gradle.tasks.run.BootRun> {
    mainClass.set("com.example.todo.ToDoApplication")   // Pfad zur Main-Klasse
}

tasks.bootRun {
    mainClass.set("com.example.todo.ToDoApplication")
}

tasks.getByName<Jar>("jar") {
    enabled = true                                      // Aktiviert das JAR-Erstellen, wenn es benötigt wird
}

tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    archiveFileName.set("todo-backend.jar")             // Setzt den Namen des erzeugten Boot JARs
}
