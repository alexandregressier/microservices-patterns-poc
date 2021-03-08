import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project

class FtgoPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.with {
            apply plugin: 'org.jetbrains.kotlin.jvm'
            apply plugin: 'org.jetbrains.kotlin.plugin.spring'
            apply plugin: 'org.jetbrains.kotlin.plugin.jpa'
            apply plugin: 'org.springframework.boot'
            apply plugin: 'io.spring.dependency-management'

            group = 'dev.foodtogo'
            java.sourceCompatibility = JavaVersion.VERSION_11

            repositories {
                jcenter()
            }

            dependencies {
                // Kotlin
                implementation 'org.jetbrains.kotlin:kotlin-stdlib-jdk8'
                implementation 'org.jetbrains.kotlin:kotlin-reflect'

                // Testing
                testImplementation 'org.springframework.boot:spring-boot-starter-test'
            }

            dependencyManagement {
                imports {
                    mavenBom "org.springframework.cloud:spring-cloud-dependencies:${ext.versionBomSpringCloud}"
                }
            }

            tasks.withType(compileKotlin.class) {
                kotlinOptions {
                    freeCompilerArgs = ['-Xjsr305=strict']
                    jvmTarget = java.sourceCompatibility
                }
            }

            test {
                useJUnitPlatform()
            }

            allOpen {
                annotation("javax.persistence.Entity")
                annotation("javax.persistence.Embeddable")
                annotation("javax.persistence.MappedSuperclass")
            }
        }
    }
}