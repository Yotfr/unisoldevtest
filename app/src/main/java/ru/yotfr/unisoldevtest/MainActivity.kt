package ru.yotfr.unisoldevtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import dagger.hilt.android.AndroidEntryPoint
import ru.yotfr.unisoldevtest.ui.categories.screen.CategoriesScreen
import ru.yotfr.unisoldevtest.ui.navigation.WallpaperNavHost

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                WallpaperNavHost()
            }
        }
    }
}