package ru.yotfr.wallpaperdownloads.usecase

import ru.yotfr.connectivity.connectivityprovider.ConnectivityProvider
import ru.yotfr.model.Wallpaper
import ru.yotfr.model.WallpaperDownload
import ru.yotfr.settings.repository.SettingsRepository
import ru.yotfr.wallpaperdownloads.wallpaperdownloader.WallpaperDownloader
import ru.yotfr.wallpaperdownloads.repository.WallpaperDownloadsRepository
import javax.inject.Inject

/**
 * [DownloadWallpaperUseCase] Возвращает false в случае если недоступен WiFi и разрешена
 * загрузка изображений только по WiFi
 */
class DownloadWallpaperUseCase @Inject constructor(
    private val wallpaperDownloader: WallpaperDownloader,
    private val wallpaperDownloadsRepository: WallpaperDownloadsRepository,
    private val settingsRepository: SettingsRepository,
    private val connectivityProvider: ConnectivityProvider
) {

    suspend operator fun invoke(wallpaper: Wallpaper): Boolean {
        val allowedOnlyWifi = settingsRepository.getAllowedOnlyWifiValue()
        val isWiFiAvailable = connectivityProvider.isWifiAvailable()
        if (!isWiFiAvailable && allowedOnlyWifi) {
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
        val wallpaperDownloadModel = WallpaperDownload(
            downloadId = downloadId,
            wallpaperId = wallpaper.id
        )
        wallpaperDownloadsRepository.addNewDownload(wallpaperDownloadModel)
        return true
    }
}