package ru.yotfr.wallpaperdetails.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import ru.yotfr.resources.R
import ru.yotfr.designsystem.theme.WallpaperTheme
import ru.yotfr.shared.model.WallpaperInstallOption
import ru.yotfr.wallpaperdetails.util.displayName

@Composable
internal fun InstallOptionsDialog(
    onDismiss: () -> Unit,
    onOkPressed: (wallpaperInstallOption: WallpaperInstallOption) -> Unit
) {
    val options = listOf(
        WallpaperInstallOption.HOME_SCREEN,
        WallpaperInstallOption.LOCK_SCREEN,
        WallpaperInstallOption.BOTH
    )
    var selectedOption by remember { mutableStateOf(WallpaperInstallOption.HOME_SCREEN) }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = stringResource(id = R.string.apply),
                style = WallpaperTheme.typography.title
            )
        },
        text = {
            Column(
                modifier = Modifier.selectableGroup()
            ) {
                options.forEach { wallpaperInstallOption ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (wallpaperInstallOption == selectedOption),
                                onClick = {
                                    selectedOption = wallpaperInstallOption
                                },
                                role = Role.RadioButton
                            )
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (wallpaperInstallOption == selectedOption), onClick = null
                        )
                        Text(
                            text = wallpaperInstallOption.displayName(),
                            style = WallpaperTheme.typography.body,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onOkPressed(selectedOption)
                }) {
                Text(
                    stringResource(id = R.string.ok),
                    style = WallpaperTheme.typography.label
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismiss()
                }) {
                Text(
                    stringResource(id = R.string.cancel),
                    style = WallpaperTheme.typography.label
                )
            }
        }
    )
}