package com.example.github_user_inspector.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.github_user_inspector.ui.theme.Blue100
import com.example.github_user_inspector.ui.theme.PorcelainBlue

@Preview(showBackground = true)
@Composable
fun GithubOctoIcon() {

    Box(
        modifier = Modifier
            .border(width = 3.dp, color = PorcelainBlue, shape = CircleShape)
            .clip(CircleShape)
            .background(color = Blue100)

    ) {
        Image(
            painter = painterResource(id = com.example.github_user_inspector.R.drawable.github_octo),
            contentDescription = "GitHub Octocat Avatar",
            modifier = Modifier.size(88.dp)
                .padding(12.dp)
        )
    }
}