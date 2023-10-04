package ru.yotfr.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import ru.yotfr.resources.R

private val Geologica = FontFamily(
    fonts = listOf(
        Font(R.font.geologica)
    )
)

@Immutable
data class WallpaperTypography(
    val title: TextStyle = TextStyle(
        fontFamily = Geologica,
        fontSize = 22.sp,
        lineHeight = 28.sp
    ),
    val label: TextStyle = TextStyle(
        fontFamily = Geologica,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
    val body: TextStyle = TextStyle(
        fontFamily = Geologica,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
    val bodyLarge: TextStyle = TextStyle(
        fontFamily = Geologica,
        fontSize = 16.sp,
        lineHeight = 22.sp
    ),
)

val LocalTypography = staticCompositionLocalOf { WallpaperTypography() }