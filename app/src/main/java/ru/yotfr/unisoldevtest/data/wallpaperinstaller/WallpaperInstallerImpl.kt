package ru.yotfr.unisoldevtest.data.wallpaperinstaller

import android.app.WallpaperManager
import android.content.Context
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.yotfr.unisoldevtest.domain.model.MResponse
import ru.yotfr.unisoldevtest.domain.model.Wallpaper
import ru.yotfr.unisoldevtest.domain.model.WallpaperInstallOption
import ru.yotfr.unisoldevtest.domain.wallpaperinstaller.WallpaperInstaller
import java.net.URL


class WallpaperInstallerImpl(
    private val context: Context
) : WallpaperInstaller {

    private val wallpaperManager = WallpaperManager.getInstance(context)
    override fun installWallpaper(
        wallpaper: Wallpaper,
        wallpaperInstallOption: WallpaperInstallOption
    ) = flow {
        emit(MResponse.Loading())
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
            emit(MResponse.Success(Unit))
        } catch (e: Exception) {
            emit(MResponse.Exception(e.message))
        }
    }.flowOn(Dispatchers.IO)
}