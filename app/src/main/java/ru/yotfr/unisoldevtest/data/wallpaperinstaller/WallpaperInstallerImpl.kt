package ru.yotfr.unisoldevtest.data.wallpaperinstaller

import android.app.WallpaperManager
import android.content.Context
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.yotfr.model.Wallpaper
import ru.yotfr.model.WallpaperInstallOption
import ru.yotfr.unisoldevtest.domain.wallpaperinstaller.WallpaperInstaller
import java.net.URL


class WallpaperInstallerImpl(
    private val context: Context
) : WallpaperInstaller {

    private val wallpaperManager = WallpaperManager.getInstance(context)
    override fun installWallpaper(
        wallpaper: ru.yotfr.model.Wallpaper,
        wallpaperInstallOption: ru.yotfr.model.WallpaperInstallOption
    ) = flow {
        emit(ru.yotfr.model.ResponseResult.Loading())
        try {
            val url = URL(wallpaper.url)
            val bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            when (wallpaperInstallOption) {
                ru.yotfr.model.WallpaperInstallOption.HOME_SCREEN -> {
                    wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_SYSTEM)
                }

                ru.yotfr.model.WallpaperInstallOption.LOCK_SCREEN -> {
                    wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_LOCK)
                }

                ru.yotfr.model.WallpaperInstallOption.BOTH -> {
                    wallpaperManager.setBitmap(bitmap)
                }
            }
            emit(ru.yotfr.model.ResponseResult.Success(Unit))
        } catch (e: Exception) {
            emit(
                ru.yotfr.model.ResponseResult.Error(
                    cause = ru.yotfr.model.ErrorCause.Unknown(
                        message = e.message ?: "Something went wrong"
                    )
                )
            )
        }
    }.flowOn(Dispatchers.IO)
}