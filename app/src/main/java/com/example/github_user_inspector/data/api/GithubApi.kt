package com.example.github_user_inspector.data.api

import com.example.github_user_inspector.data.dto.RepositoryResponse
import com.example.github_user_inspector.data.dto.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET("users/{userName}")
    suspend fun fetchUserDetails(
        @Path("userName") userName: String
    ): UserResponse

    @GET("users/{userName}/repos")
    suspend fun fetchRepositories(
        @Path("userName") userName: String
    ): List<RepositoryResponse>
}