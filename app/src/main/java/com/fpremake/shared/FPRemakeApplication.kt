package com.fpremake.shared

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class FPRemakeApplication : MultiDexApplication() {

    /**
     * Singleton object for [FPRemakeApplication] for future references
     */
    companion object {
        private lateinit var context: FPRemakeApplication

        fun getInstance(): FPRemakeApplication {
            return context
        }

    }

    override fun onCreate() {

        super.onCreate()
        context = this

        MultiDex.install(context)

        //Init Realm initialization
        //UserRealmRepository.realmInstance
    }

    open fun getBaseUrl() = "https://api.imgflip.com"
}
