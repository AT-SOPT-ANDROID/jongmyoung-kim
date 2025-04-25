package org.sopt.at.feature.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.at.domain.usecase.SignOutUseCase
import org.sopt.at.feature.mypage.state.MyPageSideEffect
import org.sopt.at.feature.mypage.state.MyPageState
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(MyPageState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<MyPageSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    fun signOut() = viewModelScope.launch {
        signOutUseCase().onSuccess {
            _sideEffect.emit(MyPageSideEffect.SignOutSucceed)
        }.onFailure {
            // TODO: 실패 처리
        }
    }
}