import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm") version "1.4.10"
}

group = "se.hulkfisk"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
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

tasks.register("day11", JavaExec::class) {
    main = "day11/BothKt"
    classpath = sourceSets.main.get().runtimeClasspath
    standardInput = System.`in`
}

application {
    mainClass.set("day11b.BKt")
}

val run: JavaExec by tasks
run.standardInput = System.`in`
