package com.example.github_user_inspector.domain.repository

import com.example.github_user_inspector.domain.entity.RepositoryEntity
import com.example.github_user_inspector.domain.entity.UserEntity

interface GithubRepository {

    suspend fun fetchUserDetails(userName: String): UserEntity

    suspend fun fetchRepositories(userName: String): List<RepositoryEntity>
}