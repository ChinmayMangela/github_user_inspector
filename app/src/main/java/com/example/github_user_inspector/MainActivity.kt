package com.example.github_user_inspector

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.github_user_inspector.data.network.NetworkUnit
import com.example.github_user_inspector.data.repository.GithubRepositoryImpl
import com.example.github_user_inspector.domain.use_cases.FetchUserDetails
import com.example.github_user_inspector.domain.use_cases.FetchUserRepositories
import com.example.github_user_inspector.presentation.screens.HomeScreen
import com.example.github_user_inspector.presentation.viewmodel.GithubViewModel
import com.example.github_user_inspector.ui.theme.Github_user_inspectorTheme
class MainActivity : ComponentActivity() {

    val githubViewModelFactory: ViewModelProvider.Factory = viewModelFactory {
        initializer {
            val githubApi = NetworkUnit.githubApi
            val githubRepository = GithubRepositoryImpl(githubApi)
            val fetchUserDetails = FetchUserDetails(githubRepository)
            val fetchUserRepositories = FetchUserRepositories(githubRepository)

            GithubViewModel(
                fetchUserDetails = fetchUserDetails,
                fetchUserRepositories = fetchUserRepositories
            )
        }
    }

    val githubViewModel: GithubViewModel by viewModels {
        githubViewModelFactory
    }
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isSearchStarted by rememberSaveable {
                mutableStateOf(false)
            }
            val userQuery by githubViewModel.userQuery.collectAsStateWithLifecycle()
            val keyboardController = LocalSoftwareKeyboardController.current
            Github_user_inspectorTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                if(isSearchStarted) {
                                    TextField(
                                        value = userQuery,
                                        onValueChange = {
                                            githubViewModel.onQueryChanged(it)
                                        },
                                        placeholder = {
                                            Text("Search Github username...")
                                        },
                                        colors = TextFieldDefaults.colors(
                                            focusedContainerColor = Color.Transparent,
                                            unfocusedContainerColor = Color.Transparent,
                                            disabledContainerColor = Color.Transparent,
                                            errorContainerColor = Color.Transparent,
                                            focusedIndicatorColor = Color.Transparent,
                                            unfocusedIndicatorColor = Color.Transparent
                                        ),
                                        keyboardOptions = KeyboardOptions(
                                            imeAction = ImeAction.Search
                                        ),
                                        keyboardActions = KeyboardActions(
                                            onSearch = {
                                                githubViewModel.fetchUserData()
                                                keyboardController?.hide()
                                                isSearchStarted = false
                                                githubViewModel.clearQuery()
                                            }
                                        )
                                    )
                                } else {
                                    Text("Github User Inspector", style = MaterialTheme.typography.headlineSmall.copy(
                                        fontWeight = FontWeight.Bold
                                    ))
                                }
                            },
                            actions = {
                                if(isSearchStarted) {
                                    IconButton(
                                        onClick = {
                                            isSearchStarted = false
                                        }
                                    ) {
                                        Icon(
                                            tint = Black,
                                            painter = painterResource(R.drawable.close),
                                            contentDescription = "Close icon",
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                } else {
                                    IconButton(
                                        onClick = {
                                            isSearchStarted = true
                                        }
                                    ) {
                                        Icon(
                                            tint = Black,
                                            painter = painterResource(R.drawable.search),
                                            contentDescription = "Open icon",
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                }
                            }
                        )
                    }
                    ) { innerPadding ->
                    HomeScreen(
                        modifier = Modifier.padding(innerPadding),
                        githubViewModel = githubViewModel
                    )
                }
            }
        }
    }
}