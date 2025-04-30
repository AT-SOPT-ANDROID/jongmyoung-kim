package org.sopt.at.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.at.core.common.type.HomeGenreType
import org.sopt.at.domain.usecase.GetHomeBannersUseCase
import org.sopt.at.domain.usecase.GetUserProfileImageUseCase
import org.sopt.at.feature.home.state.HomeSideEffect
import org.sopt.at.feature.home.state.HomeState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeBannersUseCase: GetHomeBannersUseCase,
    private val getUserProfileImageUseCase: GetUserProfileImageUseCase, // TODO: 구현 예정 없음 ㅋ ㅋ
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<HomeSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    init {
        getHomeBanners()
    }

    fun getHomeBanners() = viewModelScope.launch {
        val result = getHomeBannersUseCase()
        _uiState.value = _uiState.value.copy(
            homeSwipeBannerUrls = result.first,
            homeSmallBannerUrls = result.second
        )
    }

    fun onTabSelect(tab: HomeGenreType?) {
        _uiState.value = _uiState.value.copy(
            selectedTab = tab
        )
    }
}
