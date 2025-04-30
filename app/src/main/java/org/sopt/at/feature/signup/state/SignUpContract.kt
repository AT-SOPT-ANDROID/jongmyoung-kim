package org.sopt.at.feature.signup.state

data class SignUpState(
    val userId: String = "",
    val userPassword: String = "",
    val page: Int = 0,
)

sealed interface SignUpSideEffect {
    data object SignUpSucceed : SignUpSideEffect
    data object SignUpFailed : SignUpSideEffect
    data object InvalidId : SignUpSideEffect
    data object InvalidPassword : SignUpSideEffect
}
