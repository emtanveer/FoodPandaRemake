package com.fpremake.screens_pre_login.screen_splash.presentation


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.fpremake.R

@Composable
fun SplashScreen(navController: NavHostController) {
    SplashUIContent(navController)
}

@Composable
fun SplashUIContent(navController: NavHostController?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.pink))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier,

                fontSize = 30.sp,
                text = "Food Panda"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDefaultSplash() {
    SplashUIContent(null)
}