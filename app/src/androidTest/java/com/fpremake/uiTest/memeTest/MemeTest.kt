package com.fpremake.uiTest.memeTest


import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.ActivityAction
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fpremake.shared.presentation.MainActivity
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MemeTest {

    private val mockWebServer = MockWebServer()
    lateinit var mIdlingResource: IdlingResource

//    @get:Rule
//    val memeTestRule = MemeTestRule(mockWebServer)

    @get:Rule
    val composeTestRule= createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        val activityScenario: ActivityScenario<MainActivity> = ActivityScenario.launch(
            MainActivity::class.java
        )
        activityScenario.onActivity(object: ActivityAction<MainActivity>{
            override fun perform(activity: MainActivity) {
                mIdlingResource = activity.getIdlingResource();
                // To prove that the test fails, omit this call:
                IdlingRegistry.getInstance().register(mIdlingResource);
            }

        })
        mockWebServer.start(8080)
    }

    @After
    fun teardown() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
        mockWebServer.shutdown()
    }

//    @Test
//    fun testSuccessfulResponse() {
//        mockWebServer.dispatcher = object : Dispatcher() {
//            override fun dispatch(request: RecordedRequest): MockResponse {
//                return MockResponse()
//                    .setResponseCode(200)
//                    .setBody(FileReader.readStringFromFile("success_response.json"))
//            }
//        }
//    }

}