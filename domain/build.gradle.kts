plugins {
    kotlin("jvm")
}

group = "com.liverday"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

val junitVersion: String by rootProject.extra

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:${junitVersion}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${junitVersion}")
}