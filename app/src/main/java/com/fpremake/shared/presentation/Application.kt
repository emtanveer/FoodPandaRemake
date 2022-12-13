package com.fpremake.shared.presentation

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication

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
    }

}
