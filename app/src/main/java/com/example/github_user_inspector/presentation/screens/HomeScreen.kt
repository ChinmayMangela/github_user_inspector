package com.example.github_user_inspector.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.github_user_inspector.presentation.components.RepositoryCard
import com.example.github_user_inspector.presentation.components.UserInfoComponent
import com.example.github_user_inspector.presentation.viewmodel.GithubUiState
import com.example.github_user_inspector.presentation.viewmodel.GithubViewModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier, githubViewModel: GithubViewModel) {
    val uiState by githubViewModel.githubUiState.collectAsStateWithLifecycle()


    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (val currentState = uiState) {
            is GithubUiState.Idle -> {
                Text(
                    modifier = Modifier.padding(24.dp),
                    textAlign = TextAlign.Center,
                    text = "Click on a search button to enter a username",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }

            is GithubUiState.Loading -> {
                CircularProgressIndicator()
            }

            is GithubUiState.Error -> {
                Text(
                    modifier = Modifier.padding(24.dp),
                    text = currentState.errorMessage,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.error
                )
            }

            is GithubUiState.Success -> {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    UserInfoComponent(currentState.userEntity)

                    Spacer(Modifier.height(16.dp))

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        textAlign = TextAlign.Start,
                        text = "Repositories",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentPadding = PaddingValues(bottom = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(
                            items = currentState.repositories,
                        ) { repository ->
                            RepositoryCard(repository)
                        }
                    }
                }
            }
        }
    }
}