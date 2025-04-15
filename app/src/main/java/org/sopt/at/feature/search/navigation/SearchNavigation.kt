package org.sopt.at.feature.search.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.core.common.navigation.MainTabRoute

fun NavController.navigateToSearch(navOptions: NavOptions? = null) = navigate(Search, navOptions)

fun NavGraphBuilder.searchGraph(
    modifier: Modifier = Modifier,
) {
    composable<Search> {

    }
}

@Serializable
data object Search : MainTabRoute