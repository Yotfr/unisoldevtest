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

    //TODO: REMOVE ID REPLACE WITH DIFFERENT
    suspend operator fun invoke(wallpaper: Wallpaper) {
        val downloadId = downloader.downloadFile(wallpaper.url, wallpaper.id)
        val wallpaperDownloadModel = WallpaperDownload(
            downloadId = downloadId,
            wallpaperId = wallpaper.id
        )
        wallpaperDownloadsRepository.addNewDownload(wallpaperDownloadModel)
    }
}