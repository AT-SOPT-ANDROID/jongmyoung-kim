package org.sopt.at.feature.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import kotlinx.collections.immutable.toImmutableList
import org.sopt.at.core.designsystem.theme.AtSoptTheme
import org.sopt.at.feature.history.navigation.historyGraph
import org.sopt.at.feature.home.navigation.homeGraph
import org.sopt.at.feature.home.navigation.navigateToHome
import org.sopt.at.feature.live.navigation.liveGraph
import org.sopt.at.feature.main.component.MainBottomBar
import org.sopt.at.feature.mypage.navigation.myPageGraph
import org.sopt.at.feature.mypage.navigation.navigateToMyPage
import org.sopt.at.feature.search.navigation.searchGraph
import org.sopt.at.feature.shorts.navigation.shortsGraph
import org.sopt.at.feature.signin.navigation.navigateToSignIn
import org.sopt.at.feature.signin.navigation.signInGraph
import org.sopt.at.feature.signup.navigation.navigateToSignUp
import org.sopt.at.feature.signup.navigation.signUpGraph

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
        modifier = Modifier
            .background(color = AtSoptTheme.colors.black)
            .systemBarsPadding()
            .fillMaxSize(),
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
            navigateToMyPage = navigator.navController::navigateToMyPage,
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

        myPageGraph(
            navigateToSignIn = {
                val navOptions = navOptions {
                    popUpTo(navigator.navController.graph.startDestinationId) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
                navigator.navController.navigateToSignIn(navOptions)
            },
            modifier = modifier,
        )

        signInGraph(
            navigateToSignUp = navigator.navController::navigateToSignUp,
            navigateToHome = {
                val navOptions = navOptions {
                    popUpTo(navigator.navController.graph.startDestinationId) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
                navigator.navController.navigateToHome(navOptions)
            },
            modifier = modifier,
        )

        signUpGraph(
            navigateToSignIn = {
                val navOptions = navOptions {
                    popUpTo(navigator.navController.graph.startDestinationId) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
                navigator.navController.navigateToSignIn(navOptions)
            },
            navigateUp = navigator.navController::navigateUp,
            modifier = modifier,
        )
    }
}
