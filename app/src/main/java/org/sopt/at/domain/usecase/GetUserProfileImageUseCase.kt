package org.sopt.at.domain.usecase

import org.sopt.at.domain.repository.UserRepository
import javax.inject.Inject

class GetUserProfileImageUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(): String = userRepository.getUserProfileImage()
}
