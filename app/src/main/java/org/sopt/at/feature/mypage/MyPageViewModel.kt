package org.sopt.at.feature.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.at.domain.usecase.GetUserNicknameUseCase
import org.sopt.at.domain.usecase.PatchUserNicknameUseCase
import org.sopt.at.domain.usecase.SignOutUseCase
import org.sopt.at.feature.mypage.state.MyPageSideEffect
import org.sopt.at.feature.mypage.state.MyPageSideEffect.PatchNicknameFailed
import org.sopt.at.feature.mypage.state.MyPageState
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase,
    private val getUserNicknameUseCase: GetUserNicknameUseCase,
    private val patchUserNicknameUseCase: PatchUserNicknameUseCase,
) : ViewModel() {

    init {
        viewModelScope.launch {
            getUserNicknameUseCase().onSuccess { result ->
                result?.let { nickname ->
                    _uiState.update { it.copy(userNickname = nickname) }
                }
            }.onFailure {

            }
        }
    }

    private val _uiState = MutableStateFlow(MyPageState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<MyPageSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    fun updateText(text: String) = _uiState.update { it.copy(nicknameText = text) }

    fun patchUserNickname() = viewModelScope.launch {
        patchUserNicknameUseCase(_uiState.value.nicknameText).onSuccess {
            _uiState.update { it.copy(userNickname = _uiState.value.nicknameText) }
        }.onFailure {
            _sideEffect.emit(PatchNicknameFailed)
        }
    }

    fun signOut() = viewModelScope.launch {
        signOutUseCase().onSuccess {
            _sideEffect.emit(MyPageSideEffect.SignOutSucceed)
        }.onFailure {
            // TODO: 실패 처리
        }
    }
}
