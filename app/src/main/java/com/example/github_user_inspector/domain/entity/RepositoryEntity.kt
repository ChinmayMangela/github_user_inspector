package com.example.github_user_inspector.domain.entity

data class RepositoryEntity(
    val name: String,
    val language: String,
    val starCounts: Int,
    val description: String
)
