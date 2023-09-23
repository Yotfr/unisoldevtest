package ru.yotfr.unisoldevtest.domain.usecase

import ru.yotfr.unisoldevtest.domain.downloader.Downloader
import ru.yotfr.unisoldevtest.domain.model.Wallpaper
import javax.inject.Inject

class CheckIfFileExistsUseCase @Inject constructor(
    private val downloader: Downloader,
) {
    operator fun invoke(wallpaper: Wallpaper): Boolean {
        return downloader.checkIfFileExists(wallpaper = wallpaper)
    }
}