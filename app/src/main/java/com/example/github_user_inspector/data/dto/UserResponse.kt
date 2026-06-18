package com.example.github_user_inspector.data.dto

import com.example.github_user_inspector.domain.entity.UserEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("login")
    val userName: String?,

    val name: String?,

    @SerialName("public_repos")
    val publicRepoCount: Int?
) {
    fun toEntity(): UserEntity {
        return UserEntity(
            userName = userName ?: "Unknown",
            name = name ?: "Unknown",
            publicRepoCount = publicRepoCount ?: 0
        )
    }
}
