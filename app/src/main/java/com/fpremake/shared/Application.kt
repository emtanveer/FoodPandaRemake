package com.fpremake.shared

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.fpremake.shared.data.realm.UserRealmRepository

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
        UserRealmRepository.realmInstance
    }
}
