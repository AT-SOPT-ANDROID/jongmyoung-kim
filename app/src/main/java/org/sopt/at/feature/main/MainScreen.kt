package org.sopt.at.feature.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import kotlinx.collections.immutable.toImmutableList
import org.sopt.at.feature.history.navigation.historyGraph
import org.sopt.at.feature.home.navigation.homeGraph
import org.sopt.at.feature.live.navigation.liveGraph
import org.sopt.at.feature.main.component.MainBottomBar
import org.sopt.at.feature.search.navigation.searchGraph
import org.sopt.at.feature.shorts.navigation.shortsGraph

@Composable
fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator(),
) {
    Scaffold(
        bottomBar = {
            MainBottomBar(
                isVisible = navigator.showBottomBar(),
                tabs = MainTab.entries.toImmutableList(),
                currentTab = navigator.currentTab,
                onTabSelected = navigator::navigate,
            )
        },
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            MainNavHost(
                navigator = navigator,
                modifier = Modifier.padding(innerPadding),
            )
        }
    }
}

@Composable
private fun MainNavHost(
    navigator: MainNavigator,
    modifier: Modifier = Modifier,
) {
    NavHost(
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None },
        navController = navigator.navController,
        startDestination = navigator.startDestination,
    ) {
        homeGraph(
            modifier = modifier,
        )

        shortsGraph(
            modifier = modifier,
        )

        liveGraph(
            modifier = modifier,
        )

        searchGraph(
            modifier = modifier,
        )

        historyGraph(
            modifier = modifier,
        )
    }
}
