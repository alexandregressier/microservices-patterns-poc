plugins { `kotlin-dsl` }

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("gradle-plugin:_"))
    implementation(kotlin("allopen:_"))
    implementation(kotlin("noarg:_"))
    implementation("org.springframework.boot:spring-boot-gradle-plugin:_")
    implementation("io.spring.gradle:dependency-management-plugin:_")
}