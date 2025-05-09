package org.sopt.at.data.service

import org.sopt.at.data.dto.request.SignInRequest
import org.sopt.at.data.dto.request.SignUpRequest
import org.sopt.at.data.dto.response.BaseResponse
import org.sopt.at.data.dto.response.SignInResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/signup")
    suspend fun postSignUp(
        @Body request: SignUpRequest,
    ): BaseResponse<Unit>

    @POST("auth/signin")
    suspend fun postSignIn(
        @Body request: SignInRequest,
    ): BaseResponse<SignInResponse>
}
