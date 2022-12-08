package com.fpremake.screens_post_login.screen_dashboard.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fpremake.screens_pre_login.screen_splash.presentation.SplashUIContent

@Composable
fun DashboardScreen() {
    DashboardUIContent()
}

@Composable
fun DashboardUIContent() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)

            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Welcome to Dashboard Screen",
                    fontSize = 24.sp
                )
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewDefaultDashboard() {
    DashboardUIContent()
}