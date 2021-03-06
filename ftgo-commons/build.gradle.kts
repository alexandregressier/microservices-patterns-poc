import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    ftgo
    `java-library`
}

version = "0.1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

dependencies {}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "${java.sourceCompatibility}"
    }
}
tasks.withType<BootJar> {
    enabled = false
}
tasks.named<Jar>("jar") {
    enabled = true
}