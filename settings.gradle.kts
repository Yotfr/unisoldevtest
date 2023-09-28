pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "unisoldevtest"
include(":app")
include(":core:network")
include(":core:database")
include(":domain:categories")
include(":data:categories")
include(":domain:model")
include(":data:shared")
include(":data:categorywallpapers")
include(":data:favoritewallpapers")
include(":core:memorycache")
include(":data:wallpaperdetails")
include(":data:wallpaperdownloads")
include(":data:storageloader")
include(":data:wallpaperinstaller")
include(":core:downloader")
