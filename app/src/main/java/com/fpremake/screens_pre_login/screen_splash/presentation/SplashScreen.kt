package com.fpremake.screens_pre_login.screen_splash.presentation


import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.fpremake.navigation.navigateAndReplaceStartRoute

@Composable
fun SplashScreen(navController: NavHostController) {
    SplashUIContent(navController)
}

@Composable
fun SplashUIContent(navController: NavHostController?) {
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
                    text = "Welcome to Splash Screen",
                    fontSize = 24.sp
                )
            }

            Button(
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(top = 30.dp),
                onClick = {
                    navController?.navigateAndReplaceStartRoute("post-login")
                })
            {
                Text(text = "Go to Dashboard Screen", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDefaultSplash() {
    SplashUIContent(null)
}