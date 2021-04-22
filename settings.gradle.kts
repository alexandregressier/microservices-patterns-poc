import de.fayard.refreshVersions.bootstrapRefreshVersions

buildscript {
    repositories { gradlePluginPortal() }
    dependencies.classpath("de.fayard.refreshVersions:refreshVersions:${extra["versionPluginRefreshVersions"]}")
}
bootstrapRefreshVersions()

rootProject.name = "food-to-go"
include(
    "ftgo-commons",
    "ftgo-consumer-service",
    "ftgo-restaurant-service",
    "ftgo-order-service",
)