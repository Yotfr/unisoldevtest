package ru.yotfr.unisoldevtest.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import darkColors
import lightColors

object WallpaperTheme {
    val shape: Shape
        @Composable
        get() = LocalShapes.current
    val spacing: Spacing
        @Composable
        get() = LocalSpacing.current
    val typography: WallpaperTypography
        @Composable
        get() = LocalTypography.current
}

@Composable
fun WallpaperTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (!useDarkTheme) {
        lightColors
    } else {
        darkColors
    }

    val shape = Shape()
    val spacing = Spacing()
    val typography = WallpaperTypography()
    CompositionLocalProvider(
        LocalSpacing provides spacing,
        LocalShapes provides shape,
        LocalTypography provides typography
    ) {
        MaterialTheme(
            colorScheme = colors,
            content = content
        )
    }
}