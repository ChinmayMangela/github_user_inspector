package com.example.github_user_inspector.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.github_user_inspector.R
import com.example.github_user_inspector.domain.entity.RepositoryEntity

@Composable
fun RepositoryCard(
    repositoryEntity: RepositoryEntity
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = repositoryEntity.name,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Card(
                    shape = RoundedCornerShape(6.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = LightGray
                    ),
                ) {
                    Text(
                        text = repositoryEntity.language,
                        modifier = Modifier.padding(5.dp),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
            }
            Spacer(Modifier.height(3.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier.size(15.dp),
                    tint = DarkGray,
                    painter = painterResource(R.drawable.star),
                    contentDescription = "star"
                )
                Spacer(Modifier.width(5.dp))
                Text(
                    repositoryEntity.starCounts.toString(), style = MaterialTheme.typography
                        .bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = DarkGray
                        )
                )
            }
            Spacer(Modifier.height(10.dp))
            Text(
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                text = repositoryEntity.description
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RepositoryCardPreview() {
    RepositoryCard(RepositoryEntity("some name", "kotlin",
        12,
        "This is what i built on todays morning using kotlin and jetpack compose"))
}