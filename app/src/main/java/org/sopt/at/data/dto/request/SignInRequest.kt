package org.sopt.at.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInRequest(
    @SerialName("loginId")
    val loginId: String,
    @SerialName("password")
    val password: String,
)
