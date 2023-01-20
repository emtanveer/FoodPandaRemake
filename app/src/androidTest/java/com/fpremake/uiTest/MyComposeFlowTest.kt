package com.fpremake.uiTest

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import com.fpremake.navigation.AppNavHost
import com.fpremake.screens_pre_login.screen_landing_location.presentation.LandingLocationScreen
import com.fpremake.shared.presentation.MainActivity
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

class MyComposeFlowTest {

    @get:Rule
//    val composeTestRule = createComposeRule()
//     use createAndroidComposeRule<YourActivity>() if you need access to an activity
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun myTest() {
        // Start the app
//        composeTestRule.setContent {
//            val navController = rememberNavController()
//                LandingLocationScreen(navController)
//            navController.graph.setStartDestination("post-login")
//            AppNavHost()
//        }

        //composeTestRule.onNodeWithText("Find Restaurants and shops").assertIsDisplayed()
        composeTestRule.onNodeWithText("Enter my location").performClick()


        composeTestRule.onNodeWithText("Welcome to Dashboard Screen \uD83D\uDE3C")
            .assertIsDisplayed()
    }
}
