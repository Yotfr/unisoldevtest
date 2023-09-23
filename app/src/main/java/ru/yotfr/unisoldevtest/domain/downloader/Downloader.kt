package ru.yotfr.unisoldevtest.domain.downloader

interface Downloader {
    fun downloadFile(url: String, fileName: String): Long
    fun getDownloadStatus(downloadId: Long): Int?
}