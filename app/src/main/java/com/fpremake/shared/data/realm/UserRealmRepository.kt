package com.fpremake.shared.data.realm

import com.fpremake.screens_post_login.screen_dashboard.data.realm.User
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.RealmObject

interface UserRealmRepository {

    fun getFromRealm(id: Int): RealmResults<*>
    fun getFromRealm(): RealmResults<User>
    fun <T : RealmObject> createOrAddUserInRealm(objectToCopyRealm: T)
}