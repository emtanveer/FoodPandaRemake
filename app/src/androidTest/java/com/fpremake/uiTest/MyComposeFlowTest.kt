package com.fpremake.uiTest

import com.fpremake.R
import android.util.Log
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.fpremake.shared.presentation.MainActivity
import com.fpremake.util.MyCustomTestRule
import com.fpremake.util.TAG
import com.fpremake.util.waitUntilTimeout
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
        Log.e(TAG, "@Before")
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
        //On Location Screen
        composeTestRule.onNodeWithText("Enter my location").performClick()

        //On Dashboard Screen
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.dashboard_order_now))
            .performClick()


        //Why we using this timeout before API CALL ?
        //TODO: @SHARIK
        composeTestRule.waitUntilTimeout(1_500)
        //composeTestRule.onNodeWithText("Welcome to Dashboard Screen \uD83D\uDE3C").assertIsDisplayed()

        composeTestRule.onNodeWithText("Drake Hotline Bling").assertIsDisplayed()

        composeTestRule.waitUntilTimeout(3_500)
    }

    @After
    fun tearDown() {
        Log.e(TAG, "@After")
    }

    companion object {
        @BeforeClass
        @JvmStatic
        fun setupClass() {
            Log.e(TAG, "@BeforeClass")
        }

        @AfterClass
        @JvmStatic
        fun tearDownClass() {
            Log.e(TAG, "@AfterClass")
        }

        @ClassRule
        @JvmField
        val classFirst = MyCustomTestRule("@ClassRule 1")

    }
}
