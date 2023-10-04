package ru.yotfr.wallpaperinstaller.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import ru.yotfr.designsystem.theme.WallpaperTheme
import ru.yotfr.wallpaperinstaller.navigation.RootNavGraph
import ru.yotfr.wallpaperinstaller.viewmodel.MainViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val state by viewModel.state
        WindowCompat.setDecorFitsSystemWindows(window, false)


        setContent {
            WallpaperTheme(
                theme = state.theme
            ) {
                RootNavGraph()
            }
        }
    }
}