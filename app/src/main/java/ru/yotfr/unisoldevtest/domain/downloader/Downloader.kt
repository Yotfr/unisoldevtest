package ru.yotfr.unisoldevtest.domain.downloader

import ru.yotfr.model.Wallpaper

interface Downloader {

    /*
     allowedNetwork не используется,
     так при использовании VPN
     DownloadManager не распознает NETWORK_TYPE

     Проверка на network type производится в UseCase
     */
    fun downloadFile(wallpaper: ru.yotfr.model.Wallpaper): Long
    fun getDownloadStatus(downloadId: Long): Int?
    fun checkIfFileExists(wallpaper: ru.yotfr.model.Wallpaper): Boolean
}