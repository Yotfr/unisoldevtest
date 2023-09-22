package ru.yotfr.unisoldevtest.ui.theme

import ExtraColors
import LocalExtraColors
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import blackColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController
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

@OptIn(ExperimentalFoundationApi::class)
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
        LocalExtraColors provides extraColors,
        LocalOverscrollConfiguration provides null
    ) {
        val systemUiController = rememberSystemUiController()
        val blackScrim = Color(0f, 0f, 0f, 0.3f)
        SideEffect {
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = !useDarkTheme,
                isNavigationBarContrastEnforced = false,
                transformColorForLightContent = { original ->
                    blackScrim.compositeOver(original)
                }
            )
        }
        MaterialTheme(
            colorScheme = colors,
            content = content
        )
    }
}