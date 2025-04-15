package org.sopt.at.feature.home.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.core.common.navigation.MainTabRoute
import org.sopt.at.feature.home.HomeRoute

fun NavController.navigateToHome(navOptions: NavOptions? = null) = navigate(Home, navOptions)

fun NavGraphBuilder.homeGraph(
    modifier: Modifier = Modifier,
) {
    composable<Home> {
        HomeRoute()
    }
}

@Serializable
data object Home : MainTabRoute