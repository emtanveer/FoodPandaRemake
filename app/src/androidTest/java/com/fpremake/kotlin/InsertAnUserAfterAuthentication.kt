package com.fpremake.kotlin

import android.util.Log
import com.fpremake.Expectation
import com.fpremake.RealmTesting
import com.fpremake.screens_post_login.screen_dashboard.data.realm.Child
import com.fpremake.screens_post_login.screen_dashboard.data.realm.Parent
import com.fpremake.screens_post_login.screen_dashboard.data.realm.User
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.AppConfiguration
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.query.RealmResults
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.util.concurrent.CountDownLatch

class InsertAnUserAfterAuthentication : RealmTesting() {
    @JvmField
    val TAG = "authentication"

    val REALM = "realmDB"
    private val appId = "fpremakeapp-jocbl"

    @Test
    fun authenticateUserAndSaveUserInformationToRealmDataBase() {
        val expectation = Expectation()

        var result: RealmResults<User>? = null

        val testLatch = CountDownLatch(1)

        activity!!.runOnUiThread {
            val realm = Realm.open(getRealmConfig())
            val app = App.create(AppConfiguration.Builder(appId).build())

            runBlocking {
                val user = app.login(Credentials.anonymous())
                Log.e(TAG, "user Access Token: ${user.accessToken}")
                if (user.accessToken == null) {
                    Log.e(TAG, "User Access Token not Found !!")
                    expectation.fulfill()
                }
                else {
                    // means user has token
                    Log.e(TAG, "Successfully authenticated.")
                    Log.e(TAG, "Successfully logged in ${user.id}")

                    Log.e(REALM, "Successfully opened a realm at: ${realm.configuration.path}")
                    //:snippet-start:
                    try {
                        realm.writeBlocking {
                            result = this.query<User>().find()
                            result?.forEachIndexed { index, user ->
                                Log.e(
                                    REALM,
                                    "user # $index, ${user._id},${user.firstName},${user.father_id},${user.emoji}"
                                )
                            }

                        }
                        testLatch.countDown()

                        expectation.fulfill()
                    }
                    // :snippet-end:
                    catch (error: Exception) {
                        expectation.fulfill()
                    }
                }
            }
            realm.close()
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