package com.fpremake.uiTest

import android.util.Log
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
import com.fpremake.util.MyCustomTestRule
import com.fpremake.util.TAG
import com.fpremake.util.waitUntilTimeout
import kotlinx.coroutines.runBlocking
import org.junit.*

class MyComposeFlowTest {

    @get:Rule
//    val composeTestRule = createComposeRule()
//     use createAndroidComposeRule<YourActivity>() if you need access to an activity
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    //our Custom Test Rule:
    @get:Rule
    val ruleFirst = MyCustomTestRule("@Rule 1")

    @Before
    fun setup() {
        Log.e(TAG,"@Before")
    }
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

        composeTestRule.waitUntilTimeout(5_000)

        composeTestRule.onNodeWithText("Welcome to Dashboard Screen \uD83D\uDE3C").assertIsDisplayed()
    }

    @After
    fun tearDown() {
        Log.e(TAG,"@After")
    }

    companion object {
        @BeforeClass
        @JvmStatic
        fun setupClass() {
            Log.e(TAG,"@BeforeClass")
        }

        @AfterClass
        @JvmStatic
        fun tearDownClass() {
            Log.e(TAG,"@AfterClass")
        }

        @ClassRule
        @JvmField
        val classFirst = MyCustomTestRule("@ClassRule 1")

    }
}
