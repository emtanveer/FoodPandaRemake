package com.fpremake.shared.data.realm

import com.fpremake.screens_post_login.screen_dashboard.data.User
import io.realm.kotlin.Realm
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.BaseRealmObject
import io.realm.kotlin.types.RealmObject
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

interface UserRealmRepository {

    fun getFromRealm(id: Int): RealmResults<*>
    fun getFromRealm(): RealmResults<User>
    fun <T : RealmObject> createOrAddUserInRealm(objectToCopyRealm: T)
}