package com.fpremake.screens_pre_login.screen_landing_location.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fpremake.R

@Composable
fun LandingLocationScreen() {
    LandingLocationUiContent()
}

@Composable
fun LandingLocationUiContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        Column(
            modifier = Modifier.weight(3F),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.location_logo),
                contentDescription = "location Logo",
                modifier = Modifier.size(100.dp,100.dp).background(Color.Transparent),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.size(40.dp))
            Text(
                text = "Find Restaurants and shops",
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
            )
            Text(
                text = "near you!",
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(text = "By Allowing Location access, you can search for", fontSize = 16.sp)
            Text(text = "restaurants and shops near you and receive", fontSize = 16.sp)
            Text(text = "more accurate delivery.", fontSize = 16.sp)
        }
        Column(verticalArrangement = Arrangement.Bottom) {
            Button(modifier = Modifier
                .fillMaxWidth(),
                onClick = { },
                shape = RoundedCornerShape(8.dp)) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "Allow Location Access"
                )
            }
            Spacer(modifier = Modifier.size(20.dp))
            Button(modifier = Modifier
                .fillMaxWidth(),
                onClick = { },
                shape = RoundedCornerShape(8.dp)) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "Enter my location"
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewLandingLocation() {
    Surface(modifier = Modifier.fillMaxSize()) {
        LandingLocationUiContent()
    }
}

