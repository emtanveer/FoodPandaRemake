package com.fpremake.shared.data.realm

import android.util.Log
import com.fpremake.screens_post_login.screen_dashboard.data.User
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.BaseRealmObject
import io.realm.kotlin.types.RealmObject
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object RealmProcessor {
    var realmInstance: Realm? = null

    fun startRealm(){
        runInSafeQueue({
            try {
                val config = RealmConfiguration
                    .Builder(schema = setOf(User::class)).apply {
                    this.schemaVersion(1)
                    this.deleteRealmIfMigrationNeeded()
                    this.name("FoodPandaRealmConfig")
                }
                realmInstance = Realm.open(config.build())
            } catch(e: Error) {
                Log.e("Realm", e.message.toString())
            }
        })
    }

    // Since the threads has to be same for write operations which we used for opening Realm making it singleton with one dispatcher.
    @OptIn(DelicateCoroutinesApi::class)
    private fun runInSafeQueue(runner: () -> Any, didCatch: (Error) -> Unit = { _ -> }) {
        GlobalScope.launch {
            try {
                runner()
            } catch (e: Error) {
                didCatch(e)
            }
        }
    }

    // This is very basic example with making this Object class a generic Realm accessor so you initialize it in very first activity that your app used you can easily keep accessing it from any activity
    private inline fun <reified T: BaseRealmObject>getFromRealm(id: Int): RealmResults<T>? {
        return realmInstance?.query(T::class, "id == $0", id)?.find()
    }

    fun <T: RealmObject>createInRealm(objectToCopyRealm: T) {
        runInSafeQueue({
            realmInstance?.writeBlocking {
                copyToRealm(objectToCopyRealm)
            } !!
        })
    }


}