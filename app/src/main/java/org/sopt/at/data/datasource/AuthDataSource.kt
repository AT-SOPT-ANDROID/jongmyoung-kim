package org.sopt.at.data.datasource

import org.sopt.at.data.dto.request.SignInRequest
import org.sopt.at.data.dto.request.SignUpRequest
import org.sopt.at.data.service.AuthService
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    private val authService: AuthService,
) {
    suspend fun postSignUp(signUpRequest: SignUpRequest) =
        authService.postSignUp(signUpRequest).data

    suspend fun postSignIn(signInRequest: SignInRequest) =
        authService.postSignIn(signInRequest).data
}
