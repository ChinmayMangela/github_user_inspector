package com.example.github_user_inspector.domain.use_cases

import com.example.github_user_inspector.domain.entity.RepositoryEntity
import com.example.github_user_inspector.domain.repository.GithubRepository

class FetchUserRepositories(
    private val githubRepository: GithubRepository
) {

    suspend operator fun invoke(userName: String): Result<List<RepositoryEntity>> {
        return try {
            val repositories = githubRepository.fetchRepositories(userName)
            Result.success(repositories)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}