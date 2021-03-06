plugins { `kotlin-dsl` }

gradlePlugin {
    plugins {
        register("ftgo-plugin") {
            id = "ftgo"
            implementationClass = "FtgoPlugin"
        }
    }
}

repositories {
    jcenter()
}

dependencies {}