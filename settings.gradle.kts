pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
}
rootProject.name = "totersapp"
include (":app")
include(":core")
include(":core_data")
include(":core_domain")
include(":core_presentation")
include(":feature_characters_list")
include(":feature_character_detail")
