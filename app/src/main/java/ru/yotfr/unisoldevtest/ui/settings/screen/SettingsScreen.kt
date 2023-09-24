package ru.yotfr.unisoldevtest.ui.settings.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import ru.yotfr.unisoldevtest.R
import ru.yotfr.unisoldevtest.ui.settings.event.SettingsEvent
import ru.yotfr.unisoldevtest.ui.settings.util.displayName
import ru.yotfr.unisoldevtest.ui.settings.viewmodel.SettingsViewModel
import ru.yotfr.unisoldevtest.ui.theme.WallpaperTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    vm: SettingsViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val state by vm.state.collectAsState()

    var isChangeThemeDialogVisible by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {
            SettingsTopBar(navigateBack = navigateBack)
        }
    ) { innerPaddings ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddings)
                .padding(WallpaperTheme.spacing.medium)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            isChangeThemeDialogVisible = true
                        }
                        .padding(
                            vertical = WallpaperTheme.spacing.medium
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.appearance),
                        style = WallpaperTheme.typography.bodyLarge
                    )
                    Text(
                        text = state.currentTheme.displayName(),
                        style = WallpaperTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
            AnimatedVisibility(
                visible = isChangeThemeDialogVisible,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                ChangeThemeDialog(
                    currentTheme = state.currentTheme,
                    onDismiss = { isChangeThemeDialogVisible = false },
                    onOkPressed = { newTheme ->
                        vm.onEvent(
                            SettingsEvent.ThemeChanged(
                                newTheme = newTheme
                            )
                        )
                        isChangeThemeDialogVisible = false
                    }
                )
            }
        }
    }
}