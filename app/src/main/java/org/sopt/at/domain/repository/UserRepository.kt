package org.sopt.at.domain.repository

interface UserRepository {
    suspend fun getUserProfileImage(): String

    suspend fun getUserNickname(): Result<String?>

    suspend fun getAllUserNickname(keyword: String?): Result<List<String>?>

    suspend fun patchUserNickname(nickname: String): Result<Unit>
}
