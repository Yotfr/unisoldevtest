package ru.yotfr.unisoldevtest.ui.root

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.yotfr.unisoldevtest.R
import ru.yotfr.unisoldevtest.ui.navigation.WallpaperNavHost
import ru.yotfr.unisoldevtest.ui.navigation.rootScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootScreen() {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar  {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                rootScreens.forEach { screen ->
                    NavigationBarItem(
                        icon = {
                            screen.iconResId?.let { id ->
                                Icon(
                                    painterResource(
                                        id = id
                                    ),
                                    contentDescription = null
                                )
                            }
                        },
                        label = {
                            screen.stringResId?.let {
                                Text(
                                    stringResource(it)
                                )
                            }
                        },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
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
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name)
                    )
                },
                actions = {
                    IconButton(
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Settings,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        WallpaperNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }

}