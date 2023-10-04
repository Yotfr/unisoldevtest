package ru.yotfr.home.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.categories.navigation.categoriesScreen
import ru.yotfr.favoritewallpapers.navigation.favoriteWallpapersScreen
import ru.yotfr.resources.R
import ru.yotfr.savedwallpapers.navigation.savedWallpapersScreen
import ru.yotfr.shared.navigation.BottomBarScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(
    navigateToSettings: () -> Unit,
    navigateToWallpapersByCategory: (categoryName: String) -> Unit,
    navigateToWallpaperDetails: (wallpaperId: String) -> Unit
) {
    val navController = rememberNavController()

    val bottomBarScreens = listOf(
        BottomBarScreens.Categories,
        BottomBarScreens.FavoriteWallpapers,
        BottomBarScreens.SavedWallpapers
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val isBottomBarDestination = bottomBarScreens.any { it.route == currentDestination?.route }

    Scaffold(
        bottomBar = {
            if (isBottomBarDestination) {
                HomeBottomNavBar(
                    navController = navController,
                    bottomBarScreens = bottomBarScreens,
                    isScreenSelected = { screen ->
                        currentDestination?.hierarchy?.any {
                            it.route == screen.route
                        } == true
                    }
                )
            }
        },
        topBar = {
            if (isBottomBarDestination) {
               HomeTopAppBar(
                    onSettingsClicked = navigateToSettings,
                    title =
                    currentDestination?.route?.let {
                        when (it) {
                            BottomBarScreens.Categories.route -> {
                                stringResource(id = R.string.app_name)
                            }
                            BottomBarScreens.FavoriteWallpapers.route -> {
                                stringResource(id = R.string.favorite)
                            }
                            BottomBarScreens.SavedWallpapers.route -> {
                                stringResource(id = R.string.saved)
                            }
                            else -> {
                                ""
                            }
                        }
                    } ?: ""
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) {innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomBarScreens.Categories.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            categoriesScreen(navigateToWallpapersByCategory = navigateToWallpapersByCategory)
            favoriteWallpapersScreen(navigateToWallpaperDetails = navigateToWallpaperDetails)
            savedWallpapersScreen(navigateToWallpaperDetails = navigateToWallpaperDetails)
        }
    }
}