package org.sopt.at.feature.signin.state

data class SignInState(
    val userId: String = "",
    val userPassword: String = "",
)

sealed interface SignInSideEffect {
    data object SignInSucceed : SignInSideEffect
    data object SignInFailed : SignInSideEffect
}
