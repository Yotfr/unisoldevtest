package ru.yotfr.unisoldevtest.domain.usecase

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import ru.yotfr.wallpaperdownloads.wallpaperdownloader.WallpaperDownloader
import ru.yotfr.wallpaperdownloads.repository.WallpaperDownloadsRepository
import ru.yotfr.unisoldevtest.domain.userpreference.UserPreference
import javax.inject.Inject

/**
 * [DownloadWallpaperUseCase] Возвращает false в случае если недоступен WiFi и разрешена
 * загрузка изображений только по WiFi
 */
class DownloadWallpaperUseCase @Inject constructor(
    private val wallpaperDownloader: WallpaperDownloader,
    private val wallpaperDownloadsRepository: WallpaperDownloadsRepository,
    private val userPreference: UserPreference,
    private val connectivityManager: ConnectivityManager
) {

    suspend operator fun invoke(wallpaper: ru.yotfr.model.Wallpaper): Boolean {
        val allowedOnlyWifi = userPreference.getAllowedOnlyWifiValue()
        if (!isWiFiAvailable() && allowedOnlyWifi) {
            return false
        }
        /*
         allowedNetwork не используется,
         так при использовании VPN
         DownloadManager не распознает NETWORK_TYPE
        */
        val downloadId = wallpaperDownloader.downloadWallpaper(
            wallpaper = wallpaper
        )
        val wallpaperDownloadModel = ru.yotfr.model.WallpaperDownload(
            downloadId = downloadId,
            wallpaperId = wallpaper.id
        )
        wallpaperDownloadsRepository.addNewDownload(wallpaperDownloadModel)
        return true
    }

    private fun isWiFiAvailable(): Boolean {
        return connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            ?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true
    }
}