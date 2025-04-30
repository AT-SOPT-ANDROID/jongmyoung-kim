package org.sopt.at.feature.signup.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.core.common.navigation.Route
import org.sopt.at.feature.signup.SignUpRoute

fun NavController.navigateToSignUp(navOptions: NavOptions? = null) = navigate(SignUp, navOptions)

fun NavGraphBuilder.signUpGraph(
    navigateToSignIn: () -> Unit,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    composable<SignUp> {
        SignUpRoute(
            navigateToSignIn = navigateToSignIn,
            navigateUp = navigateUp,
            modifier = modifier,
        )
    }
}

@Serializable
data object SignUp : Route
