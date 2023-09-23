package ru.yotfr.unisoldevtest.data.downloader

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import android.webkit.MimeTypeMap
import androidx.core.net.toUri
import ru.yotfr.unisoldevtest.R
import ru.yotfr.unisoldevtest.domain.downloader.Downloader

class DownloaderImpl(
    private val context: Context
) : Downloader {

    private val downloadManager = context.getSystemService(DownloadManager::class.java)

    //TODO: Network type
    override fun downloadFile(url: String, fileName: String): Long {
        val mimeType = getMimeType(url)
        val title = fileName.plus(".").plus(mimeType.substringAfterLast("/"))
        val subPath = "${context.getString(R.string.app_name)}/$title"
        val request = DownloadManager.Request(url.toUri())
            .setMimeType(mimeType)
            // .setAllowedNetworkTypes()
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
            .setVisibleInDownloadsUi(false)
            .setTitle(title)
            .setDestinationInExternalPublicDir(
                Environment.DIRECTORY_PICTURES,
                subPath
            )
        return downloadManager.enqueue(request)
    }

    @SuppressLint("Range")
    override fun getDownloadStatus(downloadId: Long): Int? {
        val request = DownloadManager.Query()
            .setFilterById(downloadId)
        downloadManager.query(request).use {
            return if (it.count > 0) {
                it.getInt(
                    it.getColumnIndex(DownloadManager.COLUMN_STATUS)
                )
            } else null
        }
    }


    private fun getMimeType(url: String): String {
        val defaultType = "image/jpeg"
        val extension = MimeTypeMap.getFileExtensionFromUrl(url)
        return extension?.let {
            MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
        } ?: defaultType
    }


}