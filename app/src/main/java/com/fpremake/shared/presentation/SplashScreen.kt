package com.fpremake.shared.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        //region Handling Splash Screen
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {

            //Handling via Splash Screen API
//            var keepSplashOnScreen = true
//            val delay = 2000L
//
//            installSplashScreen().setKeepOnScreenCondition { keepSplashOnScreen }
//            Handler(Looper.getMainLooper()).postDelayed({ keepSplashOnScreen = false }, delay)
//
//            lifecycleScope.launchWhenCreated {
//                delay(2000)
//
//                val intent = Intent(this@SplashScreen, MainActivity::class.java)
//                startActivity(intent)
//                finish()
//            }


            //Handling via Removing and resourceSplash Screen API
            setContent {
                SplashScreenUiContent()
            }
            GlobalScope.launch { // launch new coroutine in background and continue
                delay(2000L) // non-blocking delay for 1 second (default time unit is ms)

                val intent = Intent(this@SplashScreen, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        else {
            setContent {
                SplashScreenUiContent()
            }
            GlobalScope.launch { // launch new coroutine in background and continue
                delay(2000L) // non-blocking delay for 1 second (default time unit is ms)

                val intent = Intent(this@SplashScreen, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        //endregion

        super.onCreate(savedInstanceState)
    }

    @Composable
    fun SplashScreenUiContent() {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Find Restaurants and shops",
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewSplashScreenUiContent() {
        SplashScreenUiContent()
    }
}
