package org.sopt.at.feature.mypage.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.core.common.navigation.Route
import org.sopt.at.feature.mypage.MyPageRoute

fun NavController.navigateToMyPage(navOptions: NavOptions? = null) = navigate(MyPage, navOptions)

fun NavGraphBuilder.myPageGraph(
    navigateToSignIn: () -> Unit,
    modifier: Modifier = Modifier,
) {
    composable<MyPage> {
        MyPageRoute(
            navigateToSignIn = navigateToSignIn,
            modifier = modifier,
        )
    }
}

@Serializable
data object MyPage : Route
