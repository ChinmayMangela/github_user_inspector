package com.example.github_user_inspector.presentation.viewmodel

import com.example.github_user_inspector.domain.entity.RepositoryEntity
import com.example.github_user_inspector.domain.entity.UserEntity

sealed interface GithubUiState {
    data object Idle : GithubUiState
    data object Loading: GithubUiState
    data class Success(val userEntity: UserEntity, val repositories: List<RepositoryEntity>): GithubUiState
    data class Error(val errorMessage: String): GithubUiState
}