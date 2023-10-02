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
include(":core:installer")
include(":data:settings")
include(":domain:categories")
include(":domain:categorywallpapers")
include(":domain:favoritewallpapers")
include(":domain:settings")
include(":domain:wallpaperdetails")
include(":domain:storageloader")
include(":domain:wallpaperdownloads")
include(":core:connectivity")
include(":domain:wallpaperinstaller")
include(":core:resources")
include(":core:designsystem")
include(":feature:categories")
include(":feature:shared")
include(":feature:categorywallpapers")
