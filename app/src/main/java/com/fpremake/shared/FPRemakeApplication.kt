package com.fpremake.shared

import android.util.Log
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.AppConfiguration
import io.realm.kotlin.mongodb.Credentials
import kotlinx.coroutines.runBlocking

@HiltAndroidApp
class FPRemakeApplication : MultiDexApplication() {

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
}
