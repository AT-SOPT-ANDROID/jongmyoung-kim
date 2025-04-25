package org.sopt.at.data.repositoryimpl

import kotlinx.coroutines.flow.first
import org.sopt.at.data.datasource.UserPreferencesDataSource
import org.sopt.at.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val userPreferencesDataSource: UserPreferencesDataSource,
) : AuthRepository {
    override suspend fun isUserSignedIn(): Boolean = userPreferencesDataSource.isLoggedIn.first()

    override suspend fun signIn(userId: String, userPassword: String): Result<Unit> = runCatching {
        val tempCredentials = userPreferencesDataSource.getTempUserCredentials.first()

        if (userId == tempCredentials.first && userPassword == tempCredentials.second)
            userPreferencesDataSource.saveUserCredentials(userId, userPassword)
        else throw IllegalArgumentException()
    }

    override suspend fun signUp(userId: String, userPassword: String): Result<Unit> = runCatching {
        userPreferencesDataSource.saveTempUserCredentials(userId, userPassword)
    }

    override suspend fun signOut(): Result<Unit> = runCatching { userPreferencesDataSource.clearUserData() }
}
