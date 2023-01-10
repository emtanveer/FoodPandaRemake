package com.fpremake.kotlin

import android.util.Log
import com.fpremake.BuildConfig
import com.fpremake.Expectation
import com.fpremake.RealmTesting
import com.fpremake.screens_post_login.screen_dashboard.data.Child
import com.fpremake.screens_post_login.screen_dashboard.data.Parent
import com.fpremake.screens_post_login.screen_dashboard.data.User
import com.fpremake.shared.Emojis
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.AppConfiguration
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.query.RealmSingleQuery
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

class InsertAnUserAfterAuthentication : RealmTesting() {
    private val appId = "fpremakeapp-jocbl"
    @Test
    fun authenticateUserAndSaveUserInformationToRealmDataBase() {
        val expectation = Expectation()

        var result: RealmResults<User>? = null
        activity!!.runOnUiThread {
            val realm = Realm.open(getRealmConfig())

            val app = App.create(AppConfiguration.Builder(appId).build())
            runBlocking {
                val user = app.login(Credentials.anonymous())
                Log.d("User","Successfully logged in ${user.id}")
            }
            // :snippet-start:
            try {
                realm.writeBlocking {
//                    this.copyToRealm(
//                        User().apply {
//                            this.firstName = "Tanveer"
//                            this.isComplete = false
//                            this.father_id = "2233"
//                            this.emoji = Emojis.emojis?.get(7) ?: ""
//                        }
//                    )
                    // get all items in the realm
                    //val items: RealmResults<User> = userRepository.realmInstance.query<User>().find()
                    result = this.query<User>().find()
                    result?.forEachIndexed { index, user ->
                        Log.e(
                            "realm",
                            "user # $index, ${user._id},${user.firstName},${user.father_id},${user.emoji}"
                        )
                    }
                }

                Log.e("EXAMPLE", "Successfully opened a realm at: ${realm.configuration.path}")
                expectation.fulfill()
            }
            // :snippet-end:
            catch (error: Exception) {
                expectation.fulfill()
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