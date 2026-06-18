package com.example.github_user_inspector.data.repository

import com.example.github_user_inspector.data.api.GithubApi
import com.example.github_user_inspector.domain.entity.RepositoryEntity
import com.example.github_user_inspector.domain.entity.UserEntity
import com.example.github_user_inspector.domain.repository.GithubRepository
import okio.IOException
import retrofit2.HttpException
import java.net.SocketException

class GithubRepositoryImpl(
    private val githubApi: GithubApi
) : GithubRepository {
    override suspend fun fetchUserDetails(userName: String): UserEntity {
        return try {
            val user = githubApi.fetchUserDetails(userName)
            user.toEntity()
        } catch (e: SocketException) {
            throw e
        } catch (e: HttpException) {
            throw e
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun fetchRepositories(userName: String): List<RepositoryEntity> {
        return try {
            val users = githubApi.fetchRepositories(userName)
            users.map {
                it.toEntity()
            }
        } catch (e: SocketException) {
            throw e
        } catch (e: HttpException) {
            throw e
        } catch (e: Exception) {
            throw e
        }
    }
}