package org.sopt.at.feature.signin.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.core.common.navigation.Route
import org.sopt.at.feature.signin.SignInRoute

fun NavController.navigateToSignIn(navOptions: NavOptions? = null) = navigate(SignIn, navOptions)

fun NavGraphBuilder.signInGraph(
    navigateToSignUp: () -> Unit,
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier,
) {
    composable<SignIn> {
        SignInRoute(
            navigateToSignUp = navigateToSignUp,
            navigateToHome = navigateToHome,
            modifier = modifier,
        )
    }
}

@Serializable
data object SignIn : Route
