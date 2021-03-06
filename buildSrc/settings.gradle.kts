import de.fayard.refreshVersions.bootstrapRefreshVersionsForBuildSrc

buildscript {
    repositories { gradlePluginPortal() }
    dependencies.classpath("de.fayard.refreshVersions:refreshVersions:${java.util.Properties().apply { load(
        java.io.FileInputStream("${rootDir.parent}/${Project.GRADLE_PROPERTIES}")) }["versionPluginRefreshVersions"]}")
}
bootstrapRefreshVersionsForBuildSrc()