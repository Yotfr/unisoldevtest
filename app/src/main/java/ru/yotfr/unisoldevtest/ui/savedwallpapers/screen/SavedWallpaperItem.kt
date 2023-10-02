package ru.yotfr.unisoldevtest.ui.savedwallpapers.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import ru.yotfr.model.DownloadedImages
import ru.yotfr.designsystem.theme.WallpaperTheme

@Composable
fun SavedWallpaperItem(
    downloadedImage: ru.yotfr.model.DownloadedImages,
    onClick: (String) -> Unit
) {
    Surface(
        shape = ru.yotfr.designsystem.theme.WallpaperTheme.shape.default,
        color = MaterialTheme.colorScheme.surface
    ) {
        Box(
            modifier = Modifier
                .clickable {
                    onClick(downloadedImage.modelId)
                }
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = ru.yotfr.designsystem.theme.WallpaperTheme.extraColors.placeHolderColor),
                model = downloadedImage.uri,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
            )
        }
    }
}