import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

group = "dev.foodtogo"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    jcenter()
}

dependencies {
    // Kotlin
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${extra["versionBomSpringCloud"]}")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "${java.sourceCompatibility}"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}