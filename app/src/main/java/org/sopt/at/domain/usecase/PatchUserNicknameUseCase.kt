package org.sopt.at.domain.usecase

import org.sopt.at.domain.repository.UserRepository
import javax.inject.Inject

class PatchUserNicknameUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(nickname: String) = runCatching {
        userRepository.patchUserNickname(nickname)
    }
}
