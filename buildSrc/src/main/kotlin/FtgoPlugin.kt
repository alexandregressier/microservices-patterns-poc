import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.repositories
import org.gradle.kotlin.dsl.withType

class FtgoPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            pluginManager.apply {
                apply("org.jetbrains.kotlin.jvm")
                apply("org.jetbrains.kotlin.plugin.spring")
                apply("org.springframework.boot")
                apply("io.spring.dependency-management")
            }

            group = "dev.foodtogo"

            repositories {
                jcenter()
            }

            dependencies.apply {
                // Kotlin
                add("implementation", kotlin("stdlib-jdk8"))
                add("implementation", kotlin("reflect"))

                // Testing
                add("testImplementation", "org.springframework.boot:spring-boot-starter-test")
            }

            tasks.withType<Test> {
                useJUnitPlatform()
            }
        }
    }
}