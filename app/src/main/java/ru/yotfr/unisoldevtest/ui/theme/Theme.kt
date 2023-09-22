package ru.yotfr.unisoldevtest.ui.theme

import ExtraColors
import LocalExtraColors
import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import blackColor
import darkColors
import lightColors
import placeHolderColor
import whiteColor

object WallpaperTheme {

    val extraColors: ExtraColors
        @Composable
        get() = LocalExtraColors.current
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
    val colors = if (useDarkTheme) {
        darkColors
    } else {
        lightColors
    }

    val extraColors = ExtraColors(
        onWallpaperText = whiteColor,
        wallpaperEndGradient = blackColor,
        placeHolderColor = placeHolderColor
    )

    val shape = Shape()
    val spacing = Spacing()
    val typography = WallpaperTypography()
    CompositionLocalProvider(
        LocalSpacing provides spacing,
        LocalShapes provides shape,
        LocalTypography provides typography,
        LocalExtraColors provides extraColors
    ) {
        val view = LocalView.current
        if (!view.isInEditMode) {
            val systemBarsColors = if (useDarkTheme) {
                darkColors.background.toArgb()
            } else {
                lightColors.background.toArgb()
            }
                MaterialTheme.colorScheme.background.toArgb()
            SideEffect {
                val window = (view.context as Activity).window
                window.statusBarColor = systemBarsColors
                window.navigationBarColor = systemBarsColors
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                    !useDarkTheme
                WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars =
                    !useDarkTheme
            }
        }
        MaterialTheme(
            colorScheme = colors,
            content = content
        )
    }
}