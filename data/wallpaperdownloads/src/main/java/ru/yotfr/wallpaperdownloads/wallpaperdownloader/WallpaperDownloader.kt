package ru.yotfr.wallpaperdownloads.wallpaperdownloader

import ru.yotfr.shared.model.Wallpaper

interface WallpaperDownloader {

    /*
     allowedNetwork не используется,
     так при использовании VPN
     DownloadManager не распознает NETWORK_TYPE

     Проверка на network type производится в UseCase
     */
    fun downloadWallpaper(wallpaper: Wallpaper): Long
    fun getWallpaperDownloadStatus(downloadId: Long): Int?
    fun checkIfWallpaperExists(wallpaper: Wallpaper): Boolean
}