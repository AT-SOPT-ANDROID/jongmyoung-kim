package org.sopt.at.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NicknameDto(
    @SerialName("nickname")
    val nickname: String,
)
