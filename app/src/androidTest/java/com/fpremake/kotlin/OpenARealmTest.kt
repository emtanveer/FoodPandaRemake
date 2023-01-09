package com.fpremake.kotlin


import android.util.Log
import com.fpremake.Expectation
import com.fpremake.RealmTesting
import com.fpremake.screens_post_login.screen_dashboard.data.Child
import com.fpremake.screens_post_login.screen_dashboard.data.Parent
import com.fpremake.screens_post_login.screen_dashboard.data.User
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