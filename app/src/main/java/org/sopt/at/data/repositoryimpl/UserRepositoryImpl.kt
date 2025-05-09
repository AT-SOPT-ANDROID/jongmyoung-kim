package org.sopt.at.data.repositoryimpl

import org.sopt.at.data.datasource.UserDataSource
import org.sopt.at.data.mapper.toDomain
import org.sopt.at.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource,
) : UserRepository {
    override suspend fun getUserProfileImage(): String {
        // TODO: 여기도 구현 예정 없음 ㅋ ㅋ
        return ""
    }

    override suspend fun getUserNickname() = runCatching {
        userDataSource.getUserNickname().toString()
    }

    override suspend fun getAllUserNickname(
        keyword: String?,
    ) = runCatching {
        userDataSource.getAllUserNickname(keyword)?.toDomain()
    }

    override suspend fun patchUserNickname(nickname: String) = runCatching {

    }
}