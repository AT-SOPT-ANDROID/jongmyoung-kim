package org.sopt.at.domain.usecase

import org.sopt.at.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(
        userId: String,
        userPassword: String,
    ): Result<Unit> = authRepository.signUp(userId, userPassword)
}
