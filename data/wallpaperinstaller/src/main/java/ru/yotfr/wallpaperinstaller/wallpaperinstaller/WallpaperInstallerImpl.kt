package ru.yotfr.wallpaperinstaller.wallpaperinstaller

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.yotfr.installer.installer.Installer
import ru.yotfr.installer.model.InstallResult
import ru.yotfr.model.ErrorCause
import ru.yotfr.model.ResponseResult
import ru.yotfr.model.Wallpaper
import ru.yotfr.model.WallpaperInstallOption
import javax.inject.Inject


internal class WallpaperInstallerImpl @Inject constructor(
    private val installer: Installer
) : WallpaperInstaller {

    override fun installWallpaper(
        wallpaper: Wallpaper,
        wallpaperInstallOption: WallpaperInstallOption
    ): Flow<ResponseResult<Unit>> {
        return when(wallpaperInstallOption) {
            WallpaperInstallOption.HOME_SCREEN -> {
                installer.installHomeScreen(
                    url = wallpaper.url
                ).mapResponseResult()
            }
            WallpaperInstallOption.LOCK_SCREEN -> {
                installer.installLockScreen(
                    url = wallpaper.url
                ).mapResponseResult()
            }
            WallpaperInstallOption.BOTH -> {
                installer.installHomeScreenAndLockScreen(
                    url = wallpaper.url
                ).mapResponseResult()
            }
        }
    }

    private fun Flow<InstallResult>.mapResponseResult(): Flow<ResponseResult<Unit>> {
        return this.map { installResult ->
            when(installResult) {
                is InstallResult.Error -> {
                    ResponseResult.Error(
                        cause = ErrorCause.Unknown(
                            message = installResult.error.message ?: "Something went wrong"
                        )
                    )
                }
                InstallResult.Installed -> {
                    ResponseResult.Success(Unit)
                }
                InstallResult.Installing -> {
                    ResponseResult.Loading()
                }
            }
        }
    }
}