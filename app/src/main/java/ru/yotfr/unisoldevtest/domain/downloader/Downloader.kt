package ru.yotfr.unisoldevtest.domain.downloader

import ru.yotfr.unisoldevtest.domain.model.Wallpaper

interface Downloader {
    fun downloadFile(wallpaper: Wallpaper): Long
    fun getDownloadStatus(downloadId: Long): Int?
    fun checkIfFileExists(wallpaper: Wallpaper): Boolean
}