package org.sopt.at.feature.live.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.core.common.navigation.MainTabRoute

fun NavController.navigateToLive(navOptions: NavOptions? = null) = navigate(Live, navOptions)

fun NavGraphBuilder.liveGraph(
    modifier: Modifier = Modifier,
) {
    composable<Live> {

    }
}

@Serializable
data object Live : MainTabRoute