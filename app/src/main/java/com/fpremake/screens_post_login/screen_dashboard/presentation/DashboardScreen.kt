package com.fpremake.screens_post_login.screen_dashboard.presentation

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.fpremake.screens_post_login.screen_dashboard.data.User
import com.fpremake.shared.Emojis.emojis
import com.fpremake.shared.data.realm.UserRealmRepository
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
private fun performCreateAndSaveUserInRealmDB(){
    //User data to be stored in DB(Realm)
    UserRealmRepository.createOrAddUserInRealm(User().apply {
        this.firstName = "Sharik"
        this.isComplete = false
        this.father_id = "220"
        this.emoji = emojis?.get(5) ?: ""
    })
}
private fun getAllUsersFromRealmDB(){
    // all items in the realm
    val items: RealmResults<User> = UserRealmRepository.realmInstance.query<User>().find()

    items.forEachIndexed { index, user ->
        Log.e("realm", "user # $index, ${user._id},${user.firstName},${user.father_id},${user.emoji}")
    }
}
private fun closeRealmDBConnection(scope: CoroutineScope){
    //You will face the following exception if you try to close the real:
    //Java.lang.IllegalStateException: Realm has been closed and is no longer accessible:
    //data/user/0/com.fpremake/files/FoodPandaRealmConfig
    scope.launch{
            delay(2000L) // non-blocking delay for 1 second (default time unit is ms)
            UserRealmRepository.realmInstance.close()
    }
}
//endregion

//region UI Content section for Dashboard Screen
@Composable
fun DashboardUIContent(navController: NavHostController?, userEmojiList: List<User>?) {
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

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(30.dp),
                    modifier = Modifier.height(200.dp)
                ) {

                    itemsIndexed(userEmojiList!!) { index, item ->
                        UserEmojiHolder(userEmoji = item.emoji)
                    }
                }
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