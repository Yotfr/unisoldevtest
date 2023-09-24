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
import ru.yotfr.unisoldevtest.domain.model.ThemeModel
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
    theme: ThemeModel,
    content: @Composable () -> Unit
) {

    val colors = when (theme) {
        ThemeModel.LIGHT -> {
            lightColors
        }
        ThemeModel.DARK -> {
            darkColors
        }
        ThemeModel.SYSTEM_DEFAULT -> {
            if (isSystemInDarkTheme()) {
                darkColors
            } else {
                lightColors
            }
        }
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
        ConfigureSystemBars(theme = theme)
        MaterialTheme(
            colorScheme = colors,
            content = content
        )
    }
}

@Composable
fun ConfigureSystemBars(
    theme: ThemeModel
) {
    val systemUiController = rememberSystemUiController()
    val isSystemDarkTheme = isSystemInDarkTheme()
    val blackScrim = Color(0f, 0f, 0f, 0.3f)

    val useDarkIcons = when (theme) {
        ThemeModel.SYSTEM_DEFAULT -> {
            !isSystemDarkTheme
        }
        ThemeModel.DARK -> {
            false
        }
        ThemeModel.LIGHT -> {
            true
        }
    }

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = useDarkIcons,
            isNavigationBarContrastEnforced = false,
            transformColorForLightContent = { original ->
                blackScrim.compositeOver(original)
            }
        )
    }
}