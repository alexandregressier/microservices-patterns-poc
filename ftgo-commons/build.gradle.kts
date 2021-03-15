import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    ftgo
    `java-library`
}

version = "0.1.0-SNAPSHOT"

dependencies {
    // JSON
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // JPA
    implementation("org.hibernate:hibernate-core")

    // Types
    implementation("com.googlecode.libphonenumber:libphonenumber:_")
    api("com.neovisionaries:nv-i18n:_")
}

tasks.withType<BootJar> {
    enabled = false
}
tasks.named<Jar>("jar") {
    enabled = true
}