package com.example.github_user_inspector.domain.use_cases

import com.example.github_user_inspector.domain.entity.UserEntity
import com.example.github_user_inspector.domain.repository.GithubRepository

class FetchUserDetails(
    private val githubRepository: GithubRepository
) {

    suspend operator fun invoke(userName: String): Result<UserEntity> {
        return try {
            val user = githubRepository.fetchUserDetails(userName)
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}