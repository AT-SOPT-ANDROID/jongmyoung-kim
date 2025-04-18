package org.sopt.at.feature.shorts.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.core.common.navigation.MainTabRoute
import org.sopt.at.feature.shorts.ShortsRoute

fun NavController.navigateToShorts(navOptions: NavOptions? = null) = navigate(Shorts, navOptions)

fun NavGraphBuilder.shortsGraph(
    modifier: Modifier = Modifier,
) {
    composable<Shorts> {
        ShortsRoute(
            modifier = modifier,
        )
    }
}

@Serializable
data object Shorts : MainTabRoute
