package com.fpremake

import android.util.Log
import com.fpremake.screens_post_login.screen_dashboard.data.Child
import com.fpremake.screens_post_login.screen_dashboard.data.Parent
import com.fpremake.screens_post_login.screen_dashboard.data.User
import com.fpremake.shared.Emojis
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ExampleUnitTest {
    companion object {
        // var _id: ObjectId = ObjectId.create()
        //    var isComplete: Boolean = false
        //    var firstName: String = ""
        //    var emoji: String = ""
        //    var father_id: String = ""
        //    var event : String = "default" // partition key



        //Mock data
        fun buildUser() = User().apply {
            this.firstName = "Sharik"
            this.isComplete = true
            this.father_id = "2201"
            this.emoji = Emojis.emojis?.get(5) ?: ""
        }
    }
   lateinit var realm: Realm.Companion

    @Before
    fun setUp() {
        //REALM OBJECT WITH CONFIG

        // :snippet-start: configure-a-realm-local
        val config = RealmConfiguration.Builder(
            schema =
            setOf(
                User::class,
                Parent::class,
                Child::class
            )
        )
            .name("alternate-realm-test")
            .compactOnLaunch()
            .build()

        realm.open(config)
    }

    @Test
        fun `verify correct username is retrieved`() = runTest {
        val name = "Sharik"
        runBlocking {
            try {
                realm.writeBlocking {
                    result = this.query<User>().find()
                }
            } catch (e: IllegalAccessException) {

            }
        }
    }
}