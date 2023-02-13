package com.fpremake.uiTest.memeTest


import android.support.test.espresso.IdlingRegistry
import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fpremake.uiTest.memeTest.utils.FileReader
import com.fpremake.uiTest.memeTest.utils.OkHttpProvider
import com.jakewharton.espresso.OkHttp3IdlingResource
import junit.framework.Assert.assertEquals
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*


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

        okHttp3IdlingResource = OkHttp3IdlingResource.create(
            "okhttp",
            OkHttpProvider.getOkHttpClient()
        )
        IdlingRegistry.getInstance().register(
            okHttp3IdlingResource
        )

        //script MockWebServer to return this JSON
//        val assetJson: String = AssetReaderUtil.asset(activity, "atlanta-conditions.json")
//        val assetJson: String = FileReader.readStringFromFile("success_response.json")
//
//        mockWebServer.enqueue(MockResponse().setBody(assetJson))
//
//        val okhttpMockWebServerUrl: String = mockWebServer.url("/").toString()
//        Log.e("test","okhttp mockserver URL: $okhttpMockWebServerUrl")
//
//        val serviceEndpoint = "http://127.0.0.1:" + 8080
//        Log.e("test","MockWebServer Endpoint: $serviceEndpoint")

    }

    @After
    fun teardown() {
        IdlingRegistry.getInstance().unregister(okHttp3IdlingResource)
        mockWebServer.shutdown()
    }

    @Test
    fun testSuccessfulResponse() {
//        val dispatcher = object : Dispatcher() {
//            override fun dispatch(request: RecordedRequest): MockResponse {
//                return MockResponse().setResponseCode(200)
//                    .setBody(FileReader.readStringFromFile("/assets/success_response.json"))
//            }
//        }
//        mockWebServer.dispatcher = dispatcher

        val okhttpMockWebServerUrl: String = mockWebServer.url("/").toString()
        Log.e("test", "okhttp mockserver URL: $okhttpMockWebServerUrl")
        val assetJsonToString = FileReader.readStringFromFile("success_response.json")
        val response = MockResponse().setResponseCode(200).setBody(assetJsonToString)

        assertEquals("HTTP/1.1 200 OK", response.status)
        Log.e("TEST", response.getBody()!!.readUtf8())
    }

}