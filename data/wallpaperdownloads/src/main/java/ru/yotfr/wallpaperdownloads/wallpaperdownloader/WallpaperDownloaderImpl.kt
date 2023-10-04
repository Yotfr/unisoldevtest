package ru.yotfr.wallpaperdownloads.wallpaperdownloader

import ru.yotfr.wallpaperdownloads.downloader.Downloader
import ru.yotfr.shared.model.Wallpaper
import javax.inject.Inject

internal class WallpaperDownloaderImpl @Inject constructor(
    private val downloader: Downloader
) : WallpaperDownloader {
    override fun downloadWallpaper(wallpaper: Wallpaper): Long {
        // TODO: ASSETS MODULE
        return downloader.downloadFile(
            url = wallpaper.url,
            fileName = wallpaper.id,
            pathFolderName = "WallpaperInstaller"
        )
    }

    override fun getWallpaperDownloadStatus(downloadId: Long): Int? {
        return downloader.getDownloadStatus(
            downloadId = downloadId
        )
    }

    override fun checkIfWallpaperExists(wallpaper: Wallpaper): Boolean {
        // TODO: ASSETS MODULE
        return downloader.checkIfFileExists(
            url = wallpaper.url,
            fileName = wallpaper.id,
            pathFolderName = "WallpaperInstaller"
        )
    }
}