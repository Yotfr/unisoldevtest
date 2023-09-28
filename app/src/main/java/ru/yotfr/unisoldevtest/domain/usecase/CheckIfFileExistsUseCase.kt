package ru.yotfr.unisoldevtest.domain.usecase

import ru.yotfr.downloader.downloader.Downloader
import javax.inject.Inject

class CheckIfFileExistsUseCase @Inject constructor(
    private val downloader: Downloader,
) {
    operator fun invoke(wallpaper: ru.yotfr.model.Wallpaper): Boolean {
        return downloader.checkIfFileExists(wallpaper = wallpaper)
    }
}