package org.sopt.at.feature.history.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.core.common.navigation.MainTabRoute
import org.sopt.at.feature.history.HistoryRoute

fun NavController.navigateToHistory(navOptions: NavOptions? = null) = navigate(History, navOptions)

fun NavGraphBuilder.historyGraph(
    modifier: Modifier = Modifier,
) {
    composable<History> {
        HistoryRoute(
            modifier = modifier,
        )
    }
}

@Serializable
data object History : MainTabRoute
