package com.fpremake.screens_pre_login.screen_splash.presentation


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.fpremake.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    SplashUIContent(navController)
}

@Composable
fun SplashUIContent(navController: NavHostController?) {
    LaunchedEffect(key1 = true) {
        delay(2000)
        navController?.navigate("post-login")
    }

    Image(
        painter = painterResource(id = R.drawable.foodpanda_splash_image),
        contentDescription = "Food panda Logo",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewDefaultSplash() {
    SplashUIContent(null)
}