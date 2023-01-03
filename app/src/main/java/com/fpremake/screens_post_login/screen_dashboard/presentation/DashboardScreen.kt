package com.fpremake.screens_post_login.screen_dashboard.presentation

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.fpremake.screens_post_login.screen_dashboard.data.Child
import com.fpremake.screens_post_login.screen_dashboard.data.Parent
import com.fpremake.screens_post_login.screen_dashboard.data.User
import com.fpremake.shared.Emojis.emojis
import com.fpremake.shared.data.realm.UserRealmRepository
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.notifications.DeletedObject
import io.realm.kotlin.notifications.InitialResults
import io.realm.kotlin.notifications.UpdatedObject
import io.realm.kotlin.notifications.UpdatedResults
import io.realm.kotlin.query.RealmResults
import kotlinx.coroutines.*

//region Pre-requisite setup for initializing User-Data(i.e. Emoji)
val users = List(102) { i ->
    User().apply {
        this.emoji = emojis?.get(i) ?: ""
    }
}
//endregion

@Composable
fun DashboardScreen(navController: NavHostController?) {
    val scope = rememberCoroutineScope()
    DashboardUIContent(navController = navController, userEmojiList = users)

//    SideEffect {
//        closeRealmDBConnection(scope)
//    }
}

//region Helper methods for Navigation purpose
private fun handleNavigationAfterDelay() {
    /*
    LaunchedEffect(key1 = null) {
        (Dispatchers.Main){
            delay(1000)
        }
        navController?.navigate("screen_user_location") {
//                launchSingleTop = true
//                popUpTo("screen_dashboard") {
//                    saveState = true
//                }
        }
    }
    */
}
//endregion

//region Helper methods for Realm Operations
private fun performCreateAndSaveUserInRealmDB() {
    //User data to be stored in DB(Realm)
    UserRealmRepository.createOrAddUserInRealm(User().apply {
        this.firstName = "Sharik"
        this.isComplete = true
        this.father_id = "2201"
        this.emoji = emojis?.get(5) ?: ""
    })
}

private fun getAllUsersFromRealmDB() {
    // all items in the realm
    val items: RealmResults<User> = UserRealmRepository.realmInstance.query<User>().find()

    items.forEachIndexed { index, user ->
        Log.e(
            "realm",
            "user # $index, ${user._id},${user.firstName},${user.father_id},${user.emoji}"
        )
    }
}

private fun getUserByNameFromRealmDB() {
    // all items in the realm
    val items: RealmResults<User> =
        UserRealmRepository.realmInstance.query<User>("firstName == 'Sharik'").find()

    items.forEachIndexed { index, user ->
        Log.e(
            "realm",
            "user # $index, ${user._id},${user.firstName},${user.father_id},${user.emoji}"
        )
    }
}


private fun createParent() {
    // all items in the realm
    UserRealmRepository.realmInstance.writeBlocking {
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

private fun getChildByParentName() {
    val parents = UserRealmRepository.realmInstance.query<Parent>("pName == 'Sharik'").find()
    parents.forEachIndexed { index, parent ->
        Log.e("realm", "parent # $index, ${parent.id},${parent.pName}} ${parent.childs}")
    }
}

private fun closeRealmDBConnection(scope: CoroutineScope) {
    //You will face the following exception if you try to close the real:
    //Java.lang.IllegalStateException: Realm has been closed and is no longer accessible:
    //data/user/0/com.fpremake/files/FoodPandaRealmConfig
    scope.launch {
        delay(2000L) // non-blocking delay for 1 second (default time unit is ms)
        UserRealmRepository.realmInstance.close()
    }
}
//endregion

//region UI Content section for Dashboard Screen
@Composable
fun DashboardUIContent(navController: NavHostController?, userEmojiList: List<User>?) {

    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = null) {
        // fetch all objects of a type as a flow, asynchronously
        val users = UserRealmRepository.realmInstance.query<User>().asFlow()
        users.collect { results ->
            Log.d("Collecting Flow", "")
            when (results) {
                // print out initial results
                is InitialResults -> {
                    for (item in results.list) {
                        Log.d("InitialResults", "firstName ${item.firstName}")
                    }
                }
                is UpdatedResults -> {
                    Log.d("UpdatedResults", "insertions ${results.insertions.size}")
                    Log.d("UpdatedResults", "insertionRanges ${results.insertionRanges.size}")
                    Log.d("UpdatedResults", "changes ${results.changes.size}")
                    Log.d("UpdatedResults", "changeRanges ${results.changeRanges.size}")
                    Log.d("UpdatedResults", "deletions ${results.deletions.size}")          //FIFO
                    Log.d("UpdatedResults", "deletionRanges.size ${results.deletionRanges.size}")
                }
                /*is UpdatedObject<*> -> {
                    Log.d("UpdatedObject", "changedFields ${results.changedFields}")
                    Log.d("UpdatedObject", "obj ${results.obj}")
                    Log.d("UpdatedObject", "isFieldChanged ${results.isFieldChanged("firstName")}")
                }
                is DeletedObject<*> -> {
                    Log.d("DeletedObject", "obj ${results.obj}")
                }*/
                else -> {
                    // do nothing on changes
                }
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)

            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Welcome to Dashboard Screen 😼",
                    fontSize = 24.sp
                )
            }

//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(300.dp)
//                    .padding(16.dp),
//                contentAlignment = Alignment.Center
//            ) {
//                LazyVerticalGrid(
//                    columns = GridCells.Adaptive(30.dp),
//                    modifier = Modifier.height(200.dp)
//                ) {
//
//                    itemsIndexed(userEmojiList!!) { index, item ->
//                        UserEmojiHolder(userEmoji = item.emoji)
//                    }
//                }
//            }

            Button(onClick = {
                getAllUsersFromRealmDB()
            }) {
                Text(text = "Get User")
            }
            Button(onClick = {
                performCreateAndSaveUserInRealmDB()
            }) {
                Text(text = "Create User")
            }
            Button(onClick = {
                getUserByNameFromRealmDB()
            }) {
                Text(text = "Get User by name")
            }
            Button(onClick = {
                createParent()
            }) {
                Text(text = "Create Parent ")
            }
            Button(onClick = {
                getChildByParentName()
            }) {
                Text(text = "Get Parents Child only")
            }
            Button(onClick = {
                val childs =
                    UserRealmRepository.realmInstance.query<Child>("parent.pName == 'Sharik'")
                        .find()
                childs.forEachIndexed { index, child ->
                    Log.e("realm", "parent # $index, ${child.id},${child.name}}")
                }
            }) {
                Text(text = "Get Parent by child")
            }

            //Delete All User
            Button(onClick = {
                scope.launch {
                    UserRealmRepository.realmInstance.write {
                        query<User>().find().also { delete(it) }
                    }
                }
            }) {
                Text(text = "Delete All User")
            }

            //Delete User whose firstName contain Sharik
            Button(onClick = {
                scope.launch {
                    UserRealmRepository.realmInstance.write {
                        query<User>("firstName == 'Sharik'").first().find()?.also { delete(it) }
                    }
                }
            }) {
                Text(text = "Delete User by Name")
            }

            //Update User Sharik firstName to Kama
            Button(onClick = {
                scope.launch {
                    UserRealmRepository.realmInstance.write {
                        query<User>("firstName == 'Sharik'").first().find()?.also { user ->
                            user.firstName = "Kama"
                        }
                    }
                }
            }) {
                Text(text = "Update User Name")
            }
        }
    }


}

@Composable
private fun UserEmojiHolder(userEmoji: String) {
    Text(text = userEmoji)
}
//endregion

//region Compose Preview section
@Preview(showBackground = true)
@Composable
fun PreviewDefaultDashboard() {
    DashboardUIContent(navController = null, userEmojiList = users)
}
//endregion