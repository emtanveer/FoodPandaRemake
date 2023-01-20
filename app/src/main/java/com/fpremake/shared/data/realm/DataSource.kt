package com.fpremake.shared.data.realm

import com.fpremake.screens_post_login.screen_dashboard.data.realm.User
import javax.inject.Inject


open class DataSource @Inject constructor() {

    private val db = mutableMapOf<String, User>()

    fun save(user: User) = db.let { it[user.firstName] = user }

    fun get(key: String): User? = db[key]

  //  fun clear(key: String) = db.remove(key)

  //  fun clearAll() = db.clear()

}