package com.example.github_user_inspector.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.github_user_inspector.presentation.components.RepositoryCard
import com.example.github_user_inspector.presentation.components.UserInfoComponent

@Composable
fun HomeScreen(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        UserInfoComponent()
        Spacer(Modifier.height(15.dp))
        Text(
            modifier = Modifier.padding(horizontal = 15.dp),
            text  = "Repositories", style = MaterialTheme.typography
            .titleLarge.copy(
                fontWeight = FontWeight.Bold
            ))

        LazyColumn(

        ) {
            items(20) {
                RepositoryCard()
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(Modifier)
}