package org.sopt.at.domain.repository

interface UserRepository {
    suspend fun getUserProfileImage(): String
}
