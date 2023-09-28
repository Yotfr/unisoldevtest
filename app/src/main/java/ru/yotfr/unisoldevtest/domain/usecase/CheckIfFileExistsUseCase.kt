package ru.yotfr.unisoldevtest.domain.usecase

import ru.yotfr.unisoldevtest.domain.downloader.Downloader
import ru.yotfr.model.Wallpaper
import javax.inject.Inject

class CheckIfFileExistsUseCase @Inject constructor(
    private val downloader: Downloader,
) {
    operator fun invoke(wallpaper: ru.yotfr.model.Wallpaper): Boolean {
        return downloader.checkIfFileExists(wallpaper = wallpaper)
    }
}