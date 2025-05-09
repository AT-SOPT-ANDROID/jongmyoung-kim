package org.sopt.at.data.service

import org.sopt.at.data.dto.NicknameDto
import org.sopt.at.data.dto.response.AllNicknameResponse
import org.sopt.at.data.dto.response.BaseResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Query

interface UserService {
    @GET("users/me")
    suspend fun getUserNickname(): BaseResponse<NicknameDto>

    @GET("users")
    suspend fun getAllUsersNickname(
        @Query("keyword") keyword: String?,
    ): BaseResponse<AllNicknameResponse>

    @PATCH("users")
    suspend fun patchUserNickname(
        @Body request: NicknameDto,
    ): BaseResponse<Unit>
}
