package ru.yotfr.unisoldevtest.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import ru.yotfr.unisoldevtest.R

val Geologica = FontFamily(
    fonts = listOf(
        Font(R.font.geologica)
    )
)

@Immutable
data class WallpaperTypography(
    val caption: TextStyle = TextStyle(
        fontSize = 12.sp,
        fontFamily = Geologica
    ),
    val headline: TextStyle = TextStyle(
        fontSize = 18.sp,
        fontFamily = Geologica
    ),
    val body: TextStyle = TextStyle(
        fontSize = 18.sp,
        fontFamily = Geologica
    )
)

val LocalTypography = compositionLocalOf { WallpaperTypography() }