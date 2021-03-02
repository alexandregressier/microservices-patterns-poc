plugins {
    // Versions
    val versionKotlin                     = "1.4.30"
    val versionSpringBoot                 = "2.4.3"
    val versionSpringDependencyManagement = "1.0.11.RELEASE"

    // Kotlin
    kotlin("jvm")                         version versionKotlin apply false
    kotlin("plugin.spring")               version versionKotlin apply false

    // Spring
    id("org.springframework.boot")        version versionSpringBoot apply false
    id("io.spring.dependency-management") version versionSpringDependencyManagement apply false
}