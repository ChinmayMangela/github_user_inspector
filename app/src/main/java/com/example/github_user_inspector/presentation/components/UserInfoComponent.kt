package com.example.github_user_inspector.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.github_user_inspector.ui.theme.BabyBlue
import com.example.github_user_inspector.ui.theme.Blue300
import com.example.github_user_inspector.ui.theme.LightNavy

@Preview(showBackground = true)
@Composable
fun UserInfoComponent() {
    Box(
        modifier = Modifier.fillMaxWidth()
            .height(120.dp)
            .background(color = BabyBlue)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            GithubOctoIcon()

            Column() {
                Text("Chinmay Mangela", style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                ), overflow = TextOverflow.Ellipsis)
                Spacer(modifier = Modifier.height(2.dp))
                Text("@chinmaymangela", style = MaterialTheme.typography
                    .bodyLarge.copy(
                        color = Gray
                    ), overflow = TextOverflow.Ellipsis)

                Spacer(modifier = Modifier.height(5.dp))
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Blue300
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Text("Public repositories: 12", style = MaterialTheme
                            .typography
                            .titleMedium.copy(
                                color = LightNavy,
                                fontWeight = FontWeight.Bold
                            ))
                    }
                }
            }
        }
    }
}