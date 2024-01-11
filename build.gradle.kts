group = "com.example"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
    }
}

plugins {
    kotlin("jvm") apply false
    kotlin("plugin.serialization") version "1.8.20" apply false
    id("org.jetbrains.compose") apply false

}



