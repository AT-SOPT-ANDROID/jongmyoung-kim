package org.sopt.at.feature.home.state

import org.sopt.at.core.common.type.HomeGenreType

data class HomeState(
    val selectedTab: HomeGenreType? = null,
    val homeSwipeBannerUrls: List<Pair<String, String>> = emptyList(),
    val homeSmallBannerUrls: List<List<String>> = emptyList(),
    val userProfileUrl: String = "",
)

sealed interface HomeSideEffect {
    // TODO: 추후 추가 예정 없음 ㅋ ㅋ
}
