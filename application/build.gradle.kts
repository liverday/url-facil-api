import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("plugin.noarg") version "1.6.10"
}

group = "com.liverday.shortly.application"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

val junitVersion: String by rootProject.extra
val reactorVersion by extra { "3.4.13" }
val reactorExtensionsVersion by extra { "1.1.5" }
val mockitoVersion by extra { "4.5.1" }
val kotlinxCoroutinesReactorVersion by extra { "1.6.3" }

dependencies {
    implementation(project(":domain"))
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:${reactorExtensionsVersion}")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:${kotlinxCoroutinesReactorVersion}")
    testImplementation("io.projectreactor:reactor-core:${reactorVersion}")
    testImplementation("io.projectreactor:reactor-test:${reactorVersion}")
    testImplementation("org.mockito:mockito-junit-jupiter:${mockitoVersion}")
    testImplementation("org.junit.jupiter:junit-jupiter-api:${junitVersion}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${junitVersion}")
    testImplementation("io.github.glytching:junit-extensions:2.5.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
