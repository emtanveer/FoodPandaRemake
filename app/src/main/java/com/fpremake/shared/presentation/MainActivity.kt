package com.fpremake.shared.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.compose.setContent
import com.fpremake.R
import com.fpremake.navigation.AppNavHost

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //Todo Setup our Navigation(i.e. navHost) here.
            AppNavHost()
        }
    }
}