package com.fpremake.instrumentalTest

import android.app.Activity
import androidx.test.core.app.ActivityScenario
import com.fpremake.shared.data.realm.RealmTestApplicationClass
import org.junit.Assert
import org.junit.Before
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.random.Random

abstract class RealmTesting {
    @JvmField
    var scenario: ActivityScenario<RealmTestApplicationClass>? = null

    @JvmField
    var activity: Activity? = null

    @Before
    fun setUp() {
        val expectation = Expectation()
        scenario = ActivityScenario.launch(RealmTestApplicationClass::class.java)
        scenario!!.onActivity { activity ->
            this.activity = activity
            expectation.fulfill()
        }
        // ensure that setup has initialized realm before exiting
        expectation.await()
    }

    fun getRandom(): String {
        return Random.nextLong(100000L).toString()
    }
}

const val PARTITION = "Example"

/**
 * Provides the ability to block until a background task completes.
 */
class Expectation {
    private val _done = AtomicBoolean(false)

    /**
     * Fulfills the expectation, allowing the corresponding await() call to return.
     */
    fun fulfill() {
        Assert.assertFalse("Multiple calls to expectation.fulfill() unexpected", _done.get())
        _done.set(true)
    }

    /**
     * Awaits a call to "fulfill()" on another thread until the given timeout elapses.
     */
    fun await(timeoutMillis: Long = 5000L) {
        val startTimeMillis = System.currentTimeMillis()
        while (!_done.get()) {
            if (System.currentTimeMillis() - startTimeMillis > timeoutMillis) {
                Assert.fail("Timeout elapsed without a call to fulfill()")
                return
            }
        }
    }

    fun await() {
        await(5000L)
    }
}
