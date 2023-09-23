package ru.yotfr.unisoldevtest.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import ru.yotfr.unisoldevtest.domain.model.Wallpaper
import ru.yotfr.unisoldevtest.ui.theme.WallpaperTheme

@Composable
fun WallpaperItem(
    wallpaper: Wallpaper,
    onClick: (Wallpaper) -> Unit,
    onFavoriteClicked: (Wallpaper) -> Unit
) {
    Surface(
        shape = WallpaperTheme.shape.default,
        color = MaterialTheme.colorScheme.surface
    ) {
        Box(
            modifier = Modifier
                .clickable {
                    onClick(wallpaper)
                }
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = WallpaperTheme.extraColors.placeHolderColor)
                    .aspectRatio(wallpaper.aspectRatio),
                model = wallpaper.previewUrl,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
            )
            IconButton(
                onClick = {
                    onFavoriteClicked(wallpaper)
                },
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                Icon(
                    imageVector = if (wallpaper.isFavorite) Icons.Filled.Favorite
                    else Icons.Outlined.FavoriteBorder,
                    contentDescription = null,
                    tint = WallpaperTheme.extraColors.onWallpaperText
                )
            }
        }
    }
}