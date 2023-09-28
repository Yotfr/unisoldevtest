package ru.yotfr.wallpaperdownloads.downloader

interface Downloader {
    fun downloadFile(
        url: String,
        fileName: String,
        pathFolderName: String
    ): Long

    fun getDownloadStatus(downloadId: Long): Int?
    fun checkIfFileExists(
        url: String,
        fileName: String,
        pathFolderName: String
    ): Boolean
}