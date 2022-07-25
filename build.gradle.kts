plugins {
    kotlin("jvm") version "1.6.10"
}

group = "com.liverday"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

val junitVersion by extra { "5.8.2" }
val jacocoVersion by extra { "0.8.8" }

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:${junitVersion}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${junitVersion}")
}