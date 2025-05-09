package org.sopt.at.data.datasource

import org.sopt.at.data.mapper.toData
import org.sopt.at.data.service.UserService
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val userService: UserService,
) {
    suspend fun getUserNickname() = userService.getUserNickname().data

    suspend fun getAllUserNickname(
        keyword: String?,
    ) = userService.getAllUsersNickname(keyword).data

    suspend fun patchUserNickname(
        nickname: String,
    ) = userService.patchUserNickname(nickname.toData()).data
}
