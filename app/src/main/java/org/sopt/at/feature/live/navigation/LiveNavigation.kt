package org.sopt.at.feature.live.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.core.common.navigation.MainTabRoute
import org.sopt.at.feature.live.LiveRoute

fun NavController.navigateToLive(navOptions: NavOptions? = null) = navigate(Live, navOptions)

fun NavGraphBuilder.liveGraph(
    modifier: Modifier = Modifier,
) {
    composable<Live> {
        LiveRoute(
            modifier = modifier,
        )
    }
}

@Serializable
data object Live : MainTabRoute
