package com.fpremake.screens_post_login.screen_dashboard.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.invoke

@Composable
fun DashboardScreen(navController: NavHostController?) {
    DashboardUIContent(navController)

    LaunchedEffect(key1 = null) {
        (Dispatchers.Main){
            delay(1000)
        }
        navController?.navigate("screen_user_location") {
//                launchSingleTop = true
//                popUpTo("screen_dashboard") {
//                    saveState = true
//                }
        }
    }
}

@Composable
fun DashboardUIContent(navController: NavHostController?) {
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
    DashboardUIContent(null)
}