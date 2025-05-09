package org.sopt.at.data.repositoryimpl

import org.sopt.at.data.datasource.AuthDataSource
import org.sopt.at.data.datasource.TokenDataStore
import org.sopt.at.data.mapper.toData
import org.sopt.at.domain.model.SignInCredentials
import org.sopt.at.domain.model.SignUpCredentials
import org.sopt.at.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val tokenDataStore: TokenDataStore,
    private val authDataSource: AuthDataSource,
) : AuthRepository {
    override suspend fun isUserSignedIn(): Boolean = tokenDataStore.getUserId() != null

    override suspend fun postSignUp(
        signUpCredentials: SignUpCredentials,
    ): Result<Unit> = runCatching {
        authDataSource.postSignUp(signUpCredentials.toData())
    }

    override suspend fun postSignIn(
        signInCredentials: SignInCredentials,
    ): Result<Unit> = runCatching {
        authDataSource.postSignIn(signInCredentials.toData())?.let {
            tokenDataStore.setUserId(it.userId)
        }
    }

    override suspend fun signOut(): Result<Unit> = runCatching {
        tokenDataStore.clearInfo()
    }
}
