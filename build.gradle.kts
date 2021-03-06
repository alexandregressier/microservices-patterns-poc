plugins {
    kotlin("jvm") apply false
    kotlin("plugin.spring") apply false
    id("org.springframework.boot") apply false
    id("io.spring.dependency-management") apply false
}

tasks.named<Wrapper>("wrapper") {
    gradleVersion = "${project.extra["versionGradle"]}"
    distributionType = Wrapper.DistributionType.ALL
}