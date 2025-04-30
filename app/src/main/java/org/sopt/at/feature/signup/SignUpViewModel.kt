package org.sopt.at.feature.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.at.domain.usecase.SignUpUseCase
import org.sopt.at.feature.signup.state.SignUpSideEffect
import org.sopt.at.feature.signup.state.SignUpState
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(SignUpState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<SignUpSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    fun updateId(id: String) {
        _uiState.update { it.copy(userId = id) }
    }

    fun updatePassword(password: String) {
        _uiState.update { it.copy(userPassword = password) }
    }

    fun updatePage(increment: Int) {
        _uiState.update { it.copy(page = it.page + increment) }
    }

    fun signUp() = viewModelScope.launch {
        when (_uiState.value.page) {
            0 -> {
                if (_uiState.value.userId.isValidId()) updatePage(1)
                else _sideEffect.emit(SignUpSideEffect.InvalidId)
            }
            1 -> {
                if (_uiState.value.userPassword.isValidPassword()) {
                    signUpUseCase(
                        userId = _uiState.value.userId,
                        userPassword = _uiState.value.userPassword,
                    ).onSuccess {
                        _sideEffect.emit(SignUpSideEffect.SignUpSucceed)
                    }.onFailure {
                        _sideEffect.emit(SignUpSideEffect.SignUpFailed)
                    }
                } else _sideEffect.emit(SignUpSideEffect.InvalidPassword)
            }
        }
    }

    companion object {
        private fun String.isValidId(): Boolean = EMAIL_REGEX.matches(this)
        private fun String.isValidPassword(): Boolean = PASSWORD_REGEX.matches(this)

        val EMAIL_REGEX = "^[a-z0-9]{6,12}$".toRegex()
        val PASSWORD_REGEX =
            "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[~!@#$%^&*])[a-zA-Z0-9~!@#$%^&*]{8,15}$".toRegex()
    }
}
