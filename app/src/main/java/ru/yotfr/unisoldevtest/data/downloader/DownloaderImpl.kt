package ru.yotfr.unisoldevtest.data.downloader

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import android.util.Log
import android.webkit.MimeTypeMap
import androidx.core.net.toUri
import ru.yotfr.unisoldevtest.R
import ru.yotfr.unisoldevtest.domain.downloader.Downloader
import ru.yotfr.unisoldevtest.domain.model.Wallpaper
import java.io.File

class DownloaderImpl(
    private val context: Context
) : Downloader {

    private val downloadManager = context.getSystemService(DownloadManager::class.java)

    //TODO: Network type
    override fun downloadFile(wallpaper: Wallpaper): Long {
        Log.d("TEST","DOWNLOADFILE $wallpaper")
        val url = wallpaper.url
        val fileName = wallpaper.id
        val mimeType = getMimeType(url)
        val title = fileName.plus(".").plus(mimeType.substringAfterLast("/"))
        val subPath = "${context.getString(R.string.app_name)}/$title"
        Log.d("TEST","DOWNLOADFILE subpath $subPath")
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

    override fun getDownloadStatus(downloadId: Long): Int? {
        Log.d("TEST","GET STATUS $downloadId")
        val request = DownloadManager.Query()
            .setFilterById(downloadId)
        downloadManager.query(request).use {
            if(it.moveToFirst()) {
                Log.d("TEST","GET STATUS MOVE TO FIRST")
                return if (it.count > 0) {
                    Log.d("TEST","GET STATUS COUNT > 0")
                    val columnIndex = it.getColumnIndex(DownloadManager.COLUMN_STATUS)
                    val result = it.getInt(columnIndex)
                    Log.d("TEST","GET STATUS RESULT $result")
                    result
                } else null
            } else {
                Log.d("TEST","GET STATUS MOVE TO FIRST FALSE")
                return null
            }
        }
    }

    override fun checkIfFileExists(wallpaper: Wallpaper): Boolean {
        Log.d("TEST","CHECKFILEEXISTS")
        val url = wallpaper.url
        val fileName = wallpaper.id
        val mimeType = getMimeType(url)
        val title = fileName.plus(".").plus(mimeType.substringAfterLast("/"))
        val file = File(
            "${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)}" +
                    "/${context.getString(R.string.app_name)}/$title"
        )
        Log.d("TEST","CHECKFILEEXISTS path ${file.path}")
        return file.exists()
    }

    private fun getMimeType(url: String): String {
        val defaultType = "image/jpeg"
        val extension = MimeTypeMap.getFileExtensionFromUrl(url)
        return extension?.let {
            MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
        } ?: defaultType
    }


}