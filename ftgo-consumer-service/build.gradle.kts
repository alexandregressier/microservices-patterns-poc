plugins { ftgo }

version = "0.1.0-SNAPSHOT"

dependencies {
    // Projects
    implementation(project(":ftgo-commons"))

    // Common
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.cloud:spring-cloud-starter-sleuth")
    implementation("io.github.microutils:kotlin-logging:_")

    // Web
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
}