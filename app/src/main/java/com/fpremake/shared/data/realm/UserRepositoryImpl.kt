package com.fpremake.shared.data.realm

import com.fpremake.ui.screens_post.screen_dashboard.data.realm.User
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.RealmObject
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRealmRepositoryImpl @Inject constructor(
    private val dataSource: DataSource,
    var realmInstance: Realm
) :
    UserRealmRepository {

    // Since the threads has to be same for write operations which we used for opening Realm making it singleton with one dispatcher.
    @OptIn(DelicateCoroutinesApi::class)
    private fun runInSafeQueue(
        operationRunnerFunctionORBlock: () -> Any,
        didCatch: (Error) -> Unit = { _ -> }
    ) {
        GlobalScope.launch {
            try {
                operationRunnerFunctionORBlock()
            } catch (e: Error) {
                didCatch(e)
            }
        }
    }

    // This is very basic example with making this Object class a generic Realm accessor so you initialize it in very first activity that your app used you can easily keep accessing it from any activity


    fun getFirstUserInsideDB(nameToTestAgainst: String): Boolean {
//        val result = getFromRealm(id = 0)
//
//        result.forEachIndexed { index, user ->
//            // Log.e(TAG, "user # $index, ${user._id},${user.firstName},${user.father_id},${user.emoji}")
//        }
        return false
    }

    override fun getFromRealm(id: Int): RealmResults<*> {
//     val result = realmInstance.query<User>( "id == $0").find()
        val result = realmInstance.query<User>().find()
        return result
    }

    override fun getFromRealm(): RealmResults<User> {
//     val result = realmInstance.query<User>( "id == $0").find()
//        createOrAddUserInRealm(User().apply {
//            this.firstName = "Sharik"
//            this.isComplete = true
//            this.father_id = "2201"
//            this.emoji = Emojis.emojis?.get(5) ?: ""
//        })
//        return ""

        return realmInstance.query<User>().find()


    }

    override fun <T : RealmObject> createOrAddUserInRealm(objectToCopyRealm: T) {
        runInSafeQueue({
            realmInstance.writeBlocking {
                copyToRealm(objectToCopyRealm)
            }
        })
    }
}