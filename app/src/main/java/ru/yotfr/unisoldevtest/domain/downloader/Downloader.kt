package ru.yotfr.unisoldevtest.domain.downloader

import ru.yotfr.unisoldevtest.domain.model.Wallpaper

interface Downloader {

    /*
     allowedNetwork не используется,
     так при использовании VPN
     DownloadManager не распознает NETWORK_TYPE

     Проверка на network type производится в UseCase
     */
    fun downloadFile(wallpaper: Wallpaper): Long
    fun getDownloadStatus(downloadId: Long): Int?
    fun checkIfFileExists(wallpaper: Wallpaper): Boolean
}