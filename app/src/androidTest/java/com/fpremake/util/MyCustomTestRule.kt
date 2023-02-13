package com.fpremake.util

import android.util.Log
import org.junit.rules.TestWatcher
import org.junit.runner.Description

const val TAG = "CustomTestRule"
class MyCustomTestRule(private val name: String?) : TestWatcher() {

    //Before
    override fun starting(description: Description) {
        Log.e(TAG, "$name Before TestRule")
        super.starting(description)
    }

    //After
    override fun finished(description: Description) {
        Log.e(TAG, "$name After TestRule")
        super.finished(description)
    }
}