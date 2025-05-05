package org.sopt.at.feature.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.at.domain.usecase.GetSignInStatusUseCase
import org.sopt.at.domain.usecase.SignInUseCase
import org.sopt.at.feature.signin.state.SignInSideEffect
import org.sopt.at.feature.signin.state.SignInState
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val getSignInStatusUseCase: GetSignInStatusUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(SignInState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<SignInSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    init {
        checkSignInStatus() // TODO: 나중에 스플래시로 옮기기
    }

    fun checkSignInStatus() = viewModelScope.launch {
        val signInStatus = getSignInStatusUseCase()
        if (signInStatus) {
            _sideEffect.emit(SignInSideEffect.SignInSucceed)
        }
    }

    fun updateId(id: String) {
        _uiState.update { it.copy(userId = id) }
    }

    fun updatePassword(password: String) {
        _uiState.update { it.copy(userPassword = password) }
    }

    fun checkButtonEnabled(): Boolean = with(_uiState.value) {
        userId.isNotEmpty() && userPassword.isNotEmpty()
    }

    fun signIn() = viewModelScope.launch {
        signInUseCase(
            userId = _uiState.value.userId,
            userPassword = _uiState.value.userPassword,
        ).onSuccess {
            _sideEffect.emit(SignInSideEffect.SignInSucceed)
        }.onFailure {
            _sideEffect.emit(SignInSideEffect.SignInFailed)
        }
    }
}
