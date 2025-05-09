package org.sopt.at.feature.mypage.state

data class MyPageState(
    val userNickname: String = "",
)

sealed interface MyPageSideEffect {
    data object SignOutSucceed : MyPageSideEffect
}
