package com.fpremake.ui.screens_post.screen_dashboard.presentation

import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.fpremake.ui.screens_post.screen_dashboard.data.realm.Child
import com.fpremake.ui.screens_post.screen_dashboard.data.realm.Parent
import com.fpremake.ui.screens_post.screen_dashboard.data.realm.User
import com.fpremake.shared.Emojis
import com.fpremake.shared.FPRemakeApplication
import com.fpremake.shared.data.realm.UserRealmRepositoryImpl
import com.fpremake.shared.data.realm.UserRealmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.query.RealmResults
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import io.realm.kotlin.types.BaseRealmObject
import io.realm.kotlin.types.RealmObject
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class DashboardScreenViewModel @Inject constructor(
    val userRepositoryImpl: UserRealmRepositoryImpl
) : AndroidViewModel(FPRemakeApplication.getInstance()) {

    val users = userRepositoryImpl.realmInstance.query<User>().asFlow()

    //region Helper methods for Realm Operations
    fun performCreateAndSaveUserInRealmDB() {
        //User data to be stored in DB(Realm)
        userRepositoryImpl.createOrAddUserInRealm(User().apply {
            this.firstName = "Sharik"
            this.isComplete = true
            this.father_id = "2201"
            this.emoji = Emojis.emojis?.get(5) ?: ""
        })
    }

    fun getAllUsersFromRealmDB() {
        // all items in the realm
        val items: RealmResults<User> = userRepositoryImpl.realmInstance.query<User>().find()

        items.forEachIndexed { index, user ->
            Log.e(
                "realm",
                "user # $index, ${user._id},${user.firstName},${user.father_id},${user.emoji}"
            )
        }
    }

    fun getAllParentFromRealmDB() {
        // all items in the realm
        val parents: RealmResults<Parent> = userRepositoryImpl.realmInstance.query<Parent>().find()

        parents.forEachIndexed { index, parent ->
            Log.e(
                "realm",
                "parent # $index, ${parent.id},${parent.pName},${parent.childs}"
            )
        }
    }

    fun getUserByNameFromRealmDB() {
        // all items in the realm
        val items: RealmResults<User> =
            userRepositoryImpl.realmInstance.query<User>("firstName == 'Sharik'").find()

        items.forEachIndexed { index, user ->
            Log.e(
                "realm",
                "user # $index, ${user._id},${user.firstName},${user.father_id},${user.emoji}"
            )
        }
    }

    fun createParent() {
        // all items in the realm
        userRepositoryImpl.realmInstance.writeBlocking {
            val parent = Parent().apply { pName = "Sharik" }

            val child1 = Child().apply {
                name = "stinger"
                this.parent = parent
            }
            val child2 = Child().apply {
                name = "ahmed"
            }
            val child3 = Child().apply {
                name = "ali"
                this.parent = parent
            }
            copyToRealm(Parent().apply {
                pName = "Sharik"
                childs = realmListOf(
                    child1,
                    child2,
                    child3
                )
            })
        }
    }

    fun getChildByParentName() {
        val parents = userRepositoryImpl.realmInstance.query<Parent>("pName == 'Sharik'").find()
        parents.forEachIndexed { index, parent ->
            Log.e("realm", "parent # $index, ${parent.id},${parent.pName}} ${parent.childs}")
        }
    }

    fun getParentByChild() {
        val childs =
            userRepositoryImpl.realmInstance.query<Child>("parent.pName == 'Sharik'")
                .find()
        childs.forEachIndexed { index, child ->
            Log.e("realm", "parent # $index, ${child.id},${child.name}}")
        }
    }

    fun deleteAllParents() {
        viewModelScope.launch {
            userRepositoryImpl.realmInstance.write {
                query<Parent>().find().also { delete(it) }
            }
        }
    }

    fun deleteAllUsers() {
        viewModelScope.launch {
            userRepositoryImpl.realmInstance.write {
                query<User>().find().also { delete(it) }
            }
        }
    }

    fun deleteUserByname() {
        viewModelScope.launch {
            userRepositoryImpl.realmInstance.write {
                query<User>("firstName == 'Sharik'").first().find()?.also { delete(it) }
            }
        }
    }

    fun updateUserName() {
        viewModelScope.launch {
            userRepositoryImpl.realmInstance.write {
                query<User>("firstName == 'Sharik'").first().find()?.also { user ->
                    user.firstName = "Kama"
                }
            }
        }
    }

    fun closeRealmDBConnection(scope: CoroutineScope) {
        //You will face the following exception if you try to close the real:
        //Java.lang.IllegalStateException: Realm has been closed and is no longer accessible:
        //data/user/0/com.fpremake/files/FoodPandaRealmConfig
        scope.launch {
            delay(2000L) // non-blocking delay for 1 second (default time unit is ms)
            userRepositoryImpl.realmInstance.close()
        }
    }
//endregion
}