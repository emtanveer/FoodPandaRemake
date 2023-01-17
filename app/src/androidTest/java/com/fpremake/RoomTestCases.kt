package com.fpremake

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fpremake.shared.data.room.UserDao
import com.fpremake.shared.data.room.UserRoomDatabase
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class RoomTestCases {

    private lateinit var userDao: UserDao
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
       // userRoomDatabase.close()
    }

    //actual testing
    @Test
    fun checkWhetherRoomIsUpAndRunningInTheProject(){
//        Assert.assertEquals(false, userRoomDatabase.isOpen)
//        Assert.assertEquals(false, userRoomDatabase.isOpen)
        Log.e("room","room: ${userRoomDatabase.isOpen}")
    }


}