package com.fpremake.uiTest.memeTest


import android.support.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fpremake.uiTest.memeTest.utils.FileReader
import com.fpremake.uiTest.memeTest.utils.OkHttpProvider
import com.jakewharton.espresso.OkHttp3IdlingResource
import okhttp3.Response
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
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
        mockWebServer.start(8080)

        okHttp3IdlingResource = OkHttp3IdlingResource.create("okhttp", OkHttpProvider.getOkHttpClient())
        IdlingRegistry.getInstance().register(okHttp3IdlingResource)
    }

    @After
    fun teardown() {
        IdlingRegistry.getInstance().unregister(okHttp3IdlingResource)
        mockWebServer.shutdown()
    }

    @Test
    fun testSuccessfulResponse() {
        val dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse().setResponseCode(200).setBody(FileReader.readStringFromFile("success_response.json"))
            }
        }
        mockWebServer.dispatcher = dispatcher

        val request = mockWebServer.takeRequest()
        mockWebServer.dispatcher.peek().getBody()


    }

}