package com.example.github_user_inspector.data.dto

import com.example.github_user_inspector.domain.entity.RepositoryEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepositoryResponse(
    val name: String?,

    val language: String?,

    @SerialName("stargazers_count")
    val starCount: Int?,

    val description: String?
) {

    fun toEntity(): RepositoryEntity {
        return RepositoryEntity(
            name =  name ?: "Unknown",
            language = language ?: "Unknown",
            starCounts = starCount ?: 0,
            description = description ?: "No description available"
        )
    }
}
