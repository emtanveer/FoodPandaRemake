package com.fpremake.instrumentalTest.kotlin


import android.util.Log
import com.fpremake.instrumentalTest.Expectation
import com.fpremake.instrumentalTest.RealmTesting
import com.fpremake.ui.screens_post.screen_dashboard.data.realm.Child
import com.fpremake.ui.screens_post.screen_dashboard.data.realm.Parent
import com.fpremake.ui.screens_post.screen_dashboard.data.realm.User
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.junit.Test

class OpenARealmTest : RealmTesting() {

    @Test
    fun configureARealm() {
        val expectation = Expectation()
        activity!!.runOnUiThread {
            val realm = Realm.open(getRealmConfig())
            Log.e("EXAMPLE", "Successfully opened a realm at: ${realm.configuration.path}")
            // :snippet-end:
            realm.close()
            expectation.fulfill()
        }
        expectation.await()
    }

    private fun getRealmConfig(): RealmConfiguration {
        // :snippet-start: configure-a-realm-local
        return RealmConfiguration.Builder(
            schema =
            setOf(
                User::class,
                Parent::class,
                Child::class
            )
        )
            .name("alternate-realm-test")
//          .allowQueriesOnUiThread(true)
//          .allowWritesOnUiThread(true)
            .compactOnLaunch()
            .build()
    }
}