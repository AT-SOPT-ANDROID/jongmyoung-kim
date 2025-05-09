package org.sopt.at.domain.repository

import org.sopt.at.domain.model.SignInCredentials
import org.sopt.at.domain.model.SignUpCredentials

interface AuthRepository {
    suspend fun isUserSignedIn(): Boolean

    suspend fun postSignUp(signUpCredentials: SignUpCredentials): Result<Unit>

    suspend fun postSignIn(signInCredentials: SignInCredentials): Result<Unit>

    suspend fun signOut(): Result<Unit>
}
