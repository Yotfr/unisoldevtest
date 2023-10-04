package ru.yotfr.home.screen

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import ru.yotfr.shared.navigation.BottomBarScreens
import ru.yotfr.designsystem.theme.WallpaperTheme

@Composable
internal fun HomeBottomNavBar(
    navController: NavHostController,
    bottomBarScreens: List<BottomBarScreens>,
    isScreenSelected: (BottomBarScreens) -> Boolean
) {
    NavigationBar {
        bottomBarScreens.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(screen.stringResId),
                        style = WallpaperTheme.typography.label
                    )
                },
                selected = isScreenSelected(screen),
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}