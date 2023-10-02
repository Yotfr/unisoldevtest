package ru.yotfr.unisoldevtest.ui.wallpaper.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material.icons.outlined.Wallpaper
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import ru.yotfr.unisoldevtest.R
import ru.yotfr.designsystem.theme.WallpaperTheme

@Composable
fun WallpaperBottomButtonBar(
    modifier: Modifier,
    isVisible: Boolean,
    onSaveClicked: () -> Unit,
    onApplyClicked: () -> Unit,
    onFavoriteClicked: () -> Unit,
    isFavorite: Boolean
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = isVisible,
        enter = slideInVertically(
            initialOffsetY = { it * 2 },
            animationSpec = tween(durationMillis = 400)
        ),
        exit = slideOutVertically(
            targetOffsetY = { it * 2 },
            animationSpec = tween(durationMillis = 400)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .drawWithCache {
                    onDrawWithContent {
                        drawRect(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Black,
                                    Color.Transparent
                                ),
                                startY = size.height,
                                endY = 0f
                            ),
                            blendMode = BlendMode.Multiply
                        )
                        drawContent()
                    }
                }
                .navigationBarsPadding(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(
                onClick = onSaveClicked
            ) {
                Icon(
                    imageVector = Icons.Outlined.FileDownload,
                    contentDescription = stringResource(id = R.string.save),
                    tint = ru.yotfr.designsystem.theme.WallpaperTheme.extraColors.onWallpaperText
                )
            }
            IconButton(
                onClick = onApplyClicked
            ) {
                Icon(
                    imageVector = Icons.Outlined.Wallpaper,
                    contentDescription = stringResource(id = R.string.apply),
                    tint = ru.yotfr.designsystem.theme.WallpaperTheme.extraColors.onWallpaperText
                )
            }
            IconButton(
                onClick = onFavoriteClicked
            ) {
                Icon(
                    imageVector = if(isFavorite) Icons.Outlined.Favorite
                    else Icons.Outlined.FavoriteBorder,
                    contentDescription = stringResource(id = R.string.favorite),
                    tint = ru.yotfr.designsystem.theme.WallpaperTheme.extraColors.onWallpaperText
                )
            }
        }
    }
}