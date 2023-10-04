package ru.yotfr.settings.screen

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
import ru.yotfr.shared.mapper.displayName
import ru.yotfr.designsystem.theme.WallpaperTheme
import ru.yotfr.shared.model.ThemeModel

@Composable
internal fun ChangeThemeDialog(
    currentTheme: ThemeModel,
    onDismiss: () -> Unit,
    onOkPressed: (selectedTheme: ThemeModel) -> Unit
) {
    val options = listOf(
        ThemeModel.LIGHT,
        ThemeModel.DARK,
        ThemeModel.SYSTEM_DEFAULT
    )
    var selectedOption by remember { mutableStateOf(currentTheme) }
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
                options.forEach { theme ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (theme == selectedOption),
                                onClick = {
                                    selectedOption = theme
                                },
                                role = Role.RadioButton
                            )
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (theme == selectedOption), onClick = null
                        )
                        Text(
                            text = theme.displayName(),
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