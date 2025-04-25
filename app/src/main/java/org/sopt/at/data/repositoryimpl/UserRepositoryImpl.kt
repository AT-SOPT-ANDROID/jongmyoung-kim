package org.sopt.at.data.repositoryimpl

import org.sopt.at.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(

) : UserRepository {
    override suspend fun getUserProfileImage(): String {
        // TODO: 여기도 구현 예정 없음 ㅋ ㅋ
        return ""
    }
}