package com.fpremake.shared.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.VisibleForTesting
import androidx.test.espresso.IdlingResource
import com.fpremake.navigation.AppNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            //Todo Setup our Navigation(i.e. navHost) here.
            AppNavHost()
        }
    }

//    private var mIdlingResource: SimpleIdlingResource? = null
//
//    @VisibleForTesting
//    fun getIdlingResource(): IdlingResource {
//        if (mIdlingResource == null) {
//            mIdlingResource =
//                SimpleIdlingResource()
//        }
//        return mIdlingResource!!
//    }
}
