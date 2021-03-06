import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins { ftgo }

version = "0.1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

dependencies {
    // Common
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.cloud:spring-cloud-starter-sleuth")
    implementation("io.github.microutils:kotlin-logging:_")
    implementation(project(":ftgo-commons"))

    // Web
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
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