package ru.yotfr.wallpaperinstaller.wallpaperinstaller

import android.app.WallpaperManager
import android.content.Context
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.yotfr.model.ErrorCause
import ru.yotfr.model.ResponseResult
import ru.yotfr.model.Wallpaper
import ru.yotfr.model.WallpaperInstallOption
import java.net.URL


internal class WallpaperInstallerImpl(
    private val context: Context
) : WallpaperInstaller {

    private val wallpaperManager = WallpaperManager.getInstance(context)
    override fun installWallpaper(
        wallpaper: Wallpaper,
        wallpaperInstallOption: WallpaperInstallOption
    ) = flow {
        emit(ResponseResult.Loading())
        try {
            val url = URL(wallpaper.url)
            val bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            when (wallpaperInstallOption) {
                WallpaperInstallOption.HOME_SCREEN -> {
                    wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_SYSTEM)
                }

                WallpaperInstallOption.LOCK_SCREEN -> {
                    wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_LOCK)
                }

                WallpaperInstallOption.BOTH -> {
                    wallpaperManager.setBitmap(bitmap)
                }
            }
            emit(ResponseResult.Success(Unit))
        } catch (e: Exception) {
            emit(
                ResponseResult.Error(
                    cause = ErrorCause.Unknown(
                        message = e.message ?: "Something went wrong"
                    )
                )
            )
        }
    }.flowOn(Dispatchers.IO)
}