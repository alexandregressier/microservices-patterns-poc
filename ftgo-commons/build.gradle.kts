import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    ftgo
    `java-library`
}

version = "0.1.0-SNAPSHOT"

dependencies {}

tasks.withType<BootJar> {
    enabled = false
}
tasks.named<Jar>("jar") {
    enabled = true
}