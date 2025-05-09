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

    fun updateId(id: String) = _uiState.update { it.copy(userId = id) }

    fun updatePassword(password: String) = _uiState.update { it.copy(userPassword = password) }

    fun updateNickname(nickname: String) = _uiState.update { it.copy(userNickname = nickname) }

    fun updatePage(increment: Int) = _uiState.update { it.copy(page = it.page + increment) }

    fun signUp() = viewModelScope.launch {
        with(_uiState.value) {
            when (page) {
                0 -> {
                    if (userId.isValidId()) updatePage(1)
                    else _sideEffect.emit(SignUpSideEffect.InvalidId)
                }

                1 -> {
                    if (userPassword.isValidPassword()) updatePage(1)
                    else _sideEffect.emit(SignUpSideEffect.InvalidPassword)
                }

                2 -> {
                    if (userNickname.isValidNickname()) {
                        signUpUseCase(
                            userId = userId,
                            userPassword = userPassword,
                            userNickname = userNickname,
                        ).onSuccess {
                            _sideEffect.emit(SignUpSideEffect.SignUpSucceed)
                        }.onFailure {
                            android.util.Log.d("SignUpViewModel", "SignUp failed: ${it.message}")

                            _sideEffect.emit(SignUpSideEffect.SignUpFailed)
                        }
                    }
                    else _sideEffect.emit(SignUpSideEffect.InvalidNickname)
                }
            }
        }
    }

    companion object {
        private fun String.isValidId(): Boolean = EMAIL_REGEX.matches(this)
        private fun String.isValidPassword(): Boolean = PASSWORD_REGEX.matches(this)
        private fun String.isValidNickname(): Boolean = NICKNAME_REGEX.matches(this)

        val EMAIL_REGEX = "^[a-zA-Z0-9]{8,20}$".toRegex()
        val PASSWORD_REGEX = "^[a-zA-Z0-9]{8,20}$".toRegex()
        val NICKNAME_REGEX = "^[가-힣a-zA-Z0-9]{1,20}$".toRegex()
    }
}
