package org.sopt.at.feature.mypage.state

data class MyPageState(
    val userId: String = "",
)

sealed interface MyPageSideEffect {
    data object SignOutSucceed : MyPageSideEffect
}
