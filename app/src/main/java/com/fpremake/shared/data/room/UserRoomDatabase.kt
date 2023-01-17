package com.fpremake.shared.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fpremake.screens_post_login.screen_dashboard.data.room.User

@Database(entities = arrayOf(User::class), version = 1, exportSchema = true)
abstract class UserRoomDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: UserRoomDatabase? = null

        fun getDatabase(context: Context): UserRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return (
                    //IF INSTANCE is not null return the object
                    INSTANCE
                        ?:
                        //ELSE
                        synchronized(this) {
                            val instance = Room.databaseBuilder(
                                context.applicationContext,
                                UserRoomDatabase::class.java,
                                "user"
                            ).build()
                            INSTANCE = instance
                            // return instance
                            instance
                        }
                    )
        }
    }
}