package com.fpremake.uiTest.memeTest


import android.app.Instrumentation
import android.support.test.espresso.Espresso
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.IdlingResource
import android.util.Log
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.fpremake.R
import com.fpremake.shared.presentation.MainActivity
import com.fpremake.util.waitUntilTimeout
import com.jakewharton.espresso.OkHttp3IdlingResource
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MemeTest {

    private val mockWebServer = MockWebServer()
    private lateinit var okHttp3IdlingResource: OkHttp3IdlingResource

//    @get:Rule
//    val memeTestRule = MemeTestRule(mockWebServer)

//    @get:Rule
//    val composeTestRule= createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        okHttp3IdlingResource = OkHttp3IdlingResource.create(
            "okhttp",
            OkHttpProvider.getOkHttpClient()
        )
        IdlingRegistry.getInstance().register(
            okHttp3IdlingResource
        )

        mockWebServer.start(8080)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
        IdlingRegistry.getInstance().unregister(okHttp3IdlingResource)
    }

    @Test
    fun testSuccessfulResponse() {

        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(200)
                    .setBody(FileReader.readStringFromFile("/assets/success_response.json"))
            }
        }
        val abc = mockWebServer.dispatcher

        abc.peek().getBody()



    }

}