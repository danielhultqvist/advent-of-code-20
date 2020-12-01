import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
}

group = "se.hulkfisk"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "13"
}

tasks.register("day01a", JavaExec::class) {
    main = "day01/AKt"
    classpath = sourceSets.main.get().runtimeClasspath
    standardInput = System.`in`
}

tasks.register("day01b", JavaExec::class) {
    main = "day01/BKt"
    classpath = sourceSets.main.get().runtimeClasspath
    standardInput = System.`in`
}
