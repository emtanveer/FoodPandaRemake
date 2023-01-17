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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.fpremake.screens_post_login.screen_dashboard.data.realm.User
import com.fpremake.shared.Emojis.emojis
import io.realm.kotlin.notifications.InitialResults
import io.realm.kotlin.notifications.UpdatedResults
import kotlinx.coroutines.launch
import io.realm.kotlin.query.RealmResults
import kotlinx.coroutines.*
import javax.inject.Inject

//region Pre-requisite setup for initializing User-Data(i.e. Emoji)
val users = List(102) { i ->
    User().apply {
        this.emoji = emojis?.get(i) ?: ""
    }
}
//endregion


@Composable
fun DashboardScreen(navController: NavHostController?, viewModel: DashboardScreenViewModel) {
    val scope = rememberCoroutineScope()
    DashboardUIContent(navController = navController, userEmojiList = users, viewModel = viewModel)

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

//region UI Content section for Dashboard Screen
@Composable
fun DashboardUIContent(navController: NavHostController?, userEmojiList: List<User>?, viewModel: DashboardScreenViewModel) {
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = null) {
        // fetch all objects of a type as a flow, asynchronously
        //val users = UserRealmRepository.realmInstance.query<User>().asFlow()
        viewModel.users.collect { results ->
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

            Button(onClick = {
                viewModel.getAllUsersFromRealmDB()
            }) {
                Text(text = "Get Users")
            }
            Button(onClick = {
                viewModel.getAllParentFromRealmDB()
            }) {
                Text(text = "Get Parents")
            }
            Button(onClick = {
                viewModel.performCreateAndSaveUserInRealmDB()
            }) {
                Text(text = "Create User")
            }
            Button(onClick = {
                viewModel.getUserByNameFromRealmDB()
            }) {
                Text(text = "Get User by name")
            }
            Button(onClick = {
                viewModel.createParent()
            }) {
                Text(text = "Create Parent ")
            }
            Button(onClick = {
                viewModel.getChildByParentName()
            }) {
                Text(text = "Get Parents Child only")
            }
            Button(onClick = {
               viewModel.getParentByChild()
            }) {
                Text(text = "Get Parent by child")
            }

            //Delete All Parent
            Button(onClick = {
                viewModel.deleteAllParents()
            }) {
                Text(text = "Delete All Parent")
            }

            //Delete All User
            Button(onClick = {
               viewModel.deleteAllUsers()
            }) {
                Text(text = "Delete All User")
            }

            //Delete User whose firstName contain Sharik
            Button(onClick = {
                viewModel.deleteUserByname()
            }) {
                Text(text = "Delete User by Name")
            }

            //Update User Sharik firstName to Kama
            Button(onClick = {
                viewModel.updateUserName()
            }) {
                Text(text = "Update User Name")
            }
        }
    }

}

@Composable
fun UserEmojiHolder(userEmoji: String) {
    Text(text = userEmoji)
}
//endregion

//region Compose Preview section
@Preview(showBackground = true)
@Composable
fun PreviewDefaultDashboard() {
    DashboardUIContent(navController = null, userEmojiList = users, viewModel = viewModel())
}
//endregion