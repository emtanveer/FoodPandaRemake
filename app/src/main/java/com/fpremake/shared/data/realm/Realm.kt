//package com.fpremake.shared.data.realm
//
//import com.fpremake.screens_post_login.screen_dashboard.data.realm.User
//import io.realm.kotlin.Realm
//import io.realm.kotlin.RealmConfiguration
//
//object Realm {
//    fun getRealmObject(): Realm {
//        val config = RealmConfiguration.Builder(schema = setOf(User::class)).build()
//        return Realm.open(config)
//    }
//}
