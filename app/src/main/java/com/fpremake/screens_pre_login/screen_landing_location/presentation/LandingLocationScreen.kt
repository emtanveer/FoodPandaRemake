package com.fpremake.screens_pre_login.screen_landing_location.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LandingLocationScreen() {
    LandingLocationUiContent()
}

@Composable
fun LandingLocationUiContent(){
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Welcome To Landing Location Screen")
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewLandingLocation(){
    LandingLocationUiContent()
}

