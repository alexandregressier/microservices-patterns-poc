tasks.withType<Wrapper> {
    gradleVersion = "${project.extra["versionGradle"]}"
    distributionType = Wrapper.DistributionType.ALL
}