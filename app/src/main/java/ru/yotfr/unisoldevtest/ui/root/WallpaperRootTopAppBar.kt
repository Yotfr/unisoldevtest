package ru.yotfr.unisoldevtest.ui.root

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import ru.yotfr.unisoldevtest.ui.theme.WallpaperTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WallpaperRootTopAppBar(
    onSettingsClicked: () -> Unit,
    title: String
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = WallpaperTheme.typography.title
            )
        },
        actions = {
            IconButton(
                onClick = onSettingsClicked
            ) {
                Icon(
                    imageVector = Icons.Outlined.Settings,
                    contentDescription = null
                )
            }
        }
    )
}