package ru.yotfr.unisoldevtest.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import ru.yotfr.unisoldevtest.ui.root.RootScreen
import ru.yotfr.unisoldevtest.ui.theme.WallpaperTheme

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
                RootScreen()
            }
        }
    }
}