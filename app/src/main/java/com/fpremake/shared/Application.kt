package com.fpremake.shared

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.fpremake.screens_post_login.screen_dashboard.data.User
import com.fpremake.shared.data.realm.RealmProcessor
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

class Application : MultiDexApplication() {

    /**
     * Singleton object for [Application] for future references
     */
    companion object {
        private lateinit var context: Application

        fun getInstance(): Application {
            return context
        }

    }

    override fun onCreate() {

        super.onCreate()
        context = this

        MultiDex.install(context)

        //Init Realm initialization
        RealmProcessor.startRealm()
    }
}
