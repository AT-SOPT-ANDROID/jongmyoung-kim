package org.sopt.at.feature.mypage.state

data class MyPageState(
    val userNickname: String = "",
    val nicknameText: String = "",
)

sealed interface MyPageSideEffect {
    data object SignOutSucceed : MyPageSideEffect
    data object PatchNicknameFailed : MyPageSideEffect
}
