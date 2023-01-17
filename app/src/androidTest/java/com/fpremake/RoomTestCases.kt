package com.fpremake

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fpremake.screens_post_login.screen_dashboard.data.room.Child
import com.fpremake.screens_post_login.screen_dashboard.data.room.Parent
import com.fpremake.shared.data.room.ChildDao
import com.fpremake.shared.data.room.UserRoomDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.math.log

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class RoomTestCases {

    private lateinit var userDao: ChildDao
    private lateinit var userRoomDatabase: UserRoomDatabase

    @Before
    fun initRoomDatabase() {
        //Init dao and user database
        val context = ApplicationProvider.getApplicationContext<Context>()
        userRoomDatabase =
            Room.inMemoryDatabaseBuilder(context, UserRoomDatabase::class.java).build()

        userDao = userRoomDatabase.getUserDao()
    }

    @After
    @Throws(IOException::class)
    fun dispose() {
        userRoomDatabase.close()
    }


//    @Test
//    fun insertUser_returnTrue() {
//        val user1 = Child(firstName = "Tanveer")
//        val user2 = Child(firstName = "Sharik")
//        val user3 = Child(firstName = "Ali")
//        runBlocking {
//            userDao.insert(user1)
//            userDao.insert(user2)
//            userDao.insert(user3)
//            val users = userDao.getAllUsers()
//            Log.e("room","room: ${users.size}")
//        }
//        //Assert.assertNull(users)
//    }

    @Test
    fun insertChildIntoParent_returnTrue() {
        val parent = Parent(
            name = "Parent 1",
        )
        val child1 = Child(
            firstName = "Ali",
            parentId = parent.id
        )
        val child2 = Child(
            firstName = "SHARIK",
            parentId = parent.id
        )
        runBlocking {

            userDao.insertChildren(arrayListOf(child1,child2))
            userDao.insertParent(parent)

            //val childs = userDao.getAllChilds()

//            childs.forEach {
//                it.parentId = parent.id
//            }

            val parent1 = userDao.getParentWithChildren()
            Log.e("room", "parent: $parent1")
            Log.e("room", "child1: ${child1.firstName}")
            Log.e("room", "child2: ${child2.firstName}")
            parent1.forEach {
                it.child.forEach { child ->
                Log.e("room", "child: $child")
            }
            }
        }
    }
}
