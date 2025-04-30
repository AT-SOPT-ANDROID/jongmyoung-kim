package org.sopt.at.domain.repository

interface AuthRepository {
    suspend fun isUserSignedIn(): Boolean

    suspend fun signIn(userId: String, userPassword: String): Result<Unit>

    suspend fun signUp(userId: String, userPassword: String): Result<Unit>

    suspend fun signOut(): Result<Unit>
}
