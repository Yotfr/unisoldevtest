package ru.yotfr.installer.installer

import android.app.WallpaperManager
import android.content.Context
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.yotfr.installer.model.InstallResult
import java.net.URL

internal class InstallerImpl(
    private val context: Context
) : Installer {

    private val wallpaperManager = WallpaperManager.getInstance(context)

    override fun installHomeScreen(url: String): Flow<InstallResult> {
        return install(
            flag = WallpaperManager.FLAG_SYSTEM,
            resUrl = url
        )
    }

    override fun installLockScreen(url: String): Flow<InstallResult> {
        return install(
            flag = WallpaperManager.FLAG_LOCK,
            resUrl = url
        )
    }

    override fun installHomeScreenAndLockScreen(url: String): Flow<InstallResult> {
        return install(
            flag = WallpaperManager.FLAG_SYSTEM or WallpaperManager.FLAG_LOCK,
            resUrl = url
        )
    }

    private fun install(flag: Int, resUrl: String) = flow {
        emit(InstallResult.Installing)
        try {
            val url = URL(resUrl)
            val bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            wallpaperManager.setBitmap(bitmap, null, true, flag)
            emit(InstallResult.Installed)
        } catch (e: Exception) {
            emit(
                InstallResult.Error(
                    error = e
                )
            )
        }
    }.flowOn(Dispatchers.IO)
}