package com.example.github_user_inspector.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github_user_inspector.domain.use_cases.FetchUserDetails
import com.example.github_user_inspector.domain.use_cases.FetchUserRepositories
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class GithubViewModel(
    private val fetchUserDetails: FetchUserDetails,
    private val fetchUserRepositories: FetchUserRepositories
): ViewModel() {

    private val _userQuery = MutableStateFlow("")
    val userQuery = _userQuery.asStateFlow()

    private val _githubUiState = MutableStateFlow<GithubUiState>(GithubUiState.Idle)
    val githubUiState = _githubUiState.asStateFlow()

    fun onQueryChanged(newQuery: String) {
        _userQuery.value = newQuery
    }

    fun clearQuery() {
        _userQuery.value = ""
    }


    fun fetchUserData() {
        val currentQuery = _userQuery.value.trim()

        if(currentQuery.isEmpty()) return

        viewModelScope.launch {
            _githubUiState.value = GithubUiState.Loading
            try {
                val userDetailDeffer = async { fetchUserDetails.invoke(userName = currentQuery) }
                val userRepositoriesDeffer = async { fetchUserRepositories.invoke(userName = currentQuery) }

                val userResult = userDetailDeffer.await()
                val userRepositoriesResult = userRepositoriesDeffer.await()


                if(userResult.isSuccess && userRepositoriesResult.isSuccess) {
                    _githubUiState.value = GithubUiState.Success(
                        userEntity = userResult.getOrThrow(),
                        repositories = userRepositoriesResult.getOrThrow()
                    )
                } else {
                    val errorReason = userResult.exceptionOrNull()?.localizedMessage
                        ?: userRepositoriesResult.exceptionOrNull()?.localizedMessage
                        ?: "Failed to fetch github data"

                    _githubUiState.value = GithubUiState.Error(errorReason)
                }
            } catch (e: Exception) {
                _githubUiState.value = GithubUiState.Error(e.message ?: "An unexpected error occurred")
            }
        }
    }

}