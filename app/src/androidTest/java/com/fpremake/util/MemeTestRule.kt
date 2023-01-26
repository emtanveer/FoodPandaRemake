package com.fpremake.util

import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.TestWatcher
import org.junit.runner.Description

const val MEME_TAG = "CustomTestRule"

class MemeTestRule(private var mockWebServer: MockWebServer) : TestWatcher() {

    //Before
    override fun starting(description: Description) {

        mockWebServer.start(8080)
        super.starting(description)
    }

    //After
    override fun finished(description: Description) {

        mockWebServer.shutdown()
        super.finished(description)
    }
}