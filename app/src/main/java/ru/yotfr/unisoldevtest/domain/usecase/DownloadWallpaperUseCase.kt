package ru.yotfr.unisoldevtest.domain.usecase

import ru.yotfr.unisoldevtest.domain.downloader.Downloader
import ru.yotfr.unisoldevtest.domain.model.Wallpaper
import ru.yotfr.unisoldevtest.domain.model.WallpaperDownload
import ru.yotfr.unisoldevtest.domain.repository.WallpaperDownloadsRepository
import javax.inject.Inject

class DownloadWallpaperUseCase @Inject constructor(
    private val downloader: Downloader,
    private val wallpaperDownloadsRepository: WallpaperDownloadsRepository
) {

    suspend operator fun invoke(wallpaper: Wallpaper) {
        val downloadId = downloader.downloadFile(wallpaper = wallpaper)
        val wallpaperDownloadModel = WallpaperDownload(
            downloadId = downloadId,
            wallpaperId = wallpaper.id
        )
        wallpaperDownloadsRepository.addNewDownload(wallpaperDownloadModel)
    }
}