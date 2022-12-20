package com.fpremake.shared.presentation

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.fpremake.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Splash screen class implemented [SplashScreen API] for Android 12+
 * On Android 12 or below implemented splash screen without Splash api handling
 */
class SplashScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            /**
             * Uncomment this code if you want to implement Splash Screen API on Android 12+
             * Also setup the splash theme & add theme in activity tag inside a manifest file.
             */
            //region Splash Screen API on Android 12+
            /*var keepSplashOnScreen = true
            val delay = 2000L

            installSplashScreen().setKeepOnScreenCondition { keepSplashOnScreen }
            Handler(Looper.getMainLooper()).postDelayed({ keepSplashOnScreen = false }, delay)

            lifecycleScope.launchWhenCreated {
                delay(2000)

                val intent = Intent(this@SplashScreen, MainActivity::class.java)
                startActivity(intent)
                finish()
            }*/
            //endregion

            /**
             * Handling via Removing Splash theme from manifest,
             * normal flow for Android 12+ without Splash Screen Api.
             * creating custom UI For [SplashScreenUiContent()]
             */

            //region Without splash Screen API on Android 12+
            setContent {
                SplashScreenUiContent()
            }
            GlobalScope.launch { // launch new coroutine in background and continue
                delay(2000L) // non-blocking delay for 1 second (default time unit is ms)

                val intent = Intent(this@SplashScreen, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            //endregion

        } else {
            /**
             * On below Android 12 Splash Screen
             */
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
            Image(
                painter = painterResource(id = R.drawable.foodpanda_splash_image),
                contentDescription = "Food panda Logo",
                contentScale = ContentScale.Crop,
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewSplashScreenUiContent() {
        SplashScreenUiContent()
    }
}
