package org.sopt.at.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import org.sopt.at.feature.history.navigation.navigateToHistory
import org.sopt.at.feature.home.navigation.Home
import org.sopt.at.feature.home.navigation.navigateToHome
import org.sopt.at.feature.live.navigation.navigateToLive
import org.sopt.at.feature.search.navigation.navigateToSearch
import org.sopt.at.feature.shorts.navigation.navigateToShorts

class MainNavigator(
    val navController: NavHostController,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination = Home

    val currentTab: MainTab?
        @Composable get() = MainTab.find { tab ->
            currentDestination?.hasRoute(tab::class) == true
        }

    fun navigate(tab: MainTab) {
        val navOptions = navOptions {
            navController.currentDestination?.route?.let {
                popUpTo(it) {
                    inclusive = true
                    saveState = true
                }
            }
            launchSingleTop = true
            restoreState = true
        }

        when (tab) {
            MainTab.HOME -> navController.navigateToHome(navOptions)
            MainTab.SHORTS -> navController.navigateToShorts(navOptions)
            MainTab.LIVE -> navController.navigateToLive(navOptions)
            MainTab.SEARCH -> navController.navigateToSearch(navOptions)
            MainTab.HISTORY -> navController.navigateToHistory(navOptions)
        }
    }

    fun navigateUp() {
        navController.navigateUp()
    }

    @Composable
    fun showBottomBar() = MainTab.contains {
        currentDestination?.hasRoute(it::class) == true
    }
}

@Composable
fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}
