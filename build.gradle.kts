// Top-level build file where you can add configuration options common to all sub-projects/modules.

//for get graphical view of modules dependencies
apply(from="projectDependencyGraph.gradle")

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath (AppDependencies.tool_gradle)
        classpath (AppDependencies.kotlin_gradle_plugin)

        // for google services
        classpath (AppDependencies.google_services)

        // for crashlytics
        classpath (AppDependencies.crashlytics)

        // for dagger hilt
        classpath (AppDependencies.dagger_class)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

//detekt code analysis
plugins {
    this.id(Versions.detekt_ID).version(Versions.detekt_version)
    id("com.android.library") version "7.0.4" apply false
    id("org.jetbrains.kotlin.android") version "1.7.10" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}