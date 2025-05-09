package org.sopt.at.data.mapper

import org.sopt.at.data.dto.NicknameDto
import org.sopt.at.data.dto.request.SignInRequest
import org.sopt.at.data.dto.request.SignUpRequest
import org.sopt.at.data.dto.response.AllNicknameResponse
import org.sopt.at.domain.model.SignInCredentials
import org.sopt.at.domain.model.SignUpCredentials

fun SignUpCredentials.toData() = SignUpRequest(
    loginId = userId,
    password = userPassword,
    nickname = userNickname,
)

fun SignInCredentials.toData() = SignInRequest(
    loginId = userId,
    password = userPassword,
)

fun String.toData() = NicknameDto(
    nickname = this
)

fun AllNicknameResponse.toDomain() = nicknameList.map { it.toString() }.toList()