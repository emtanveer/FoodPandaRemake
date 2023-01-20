package com.fpremake.shared.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fpremake.screens_post_login.screen_dashboard.data.room.Child
import com.fpremake.screens_post_login.screen_dashboard.data.room.Parent
import com.fpremake.screens_post_login.screen_dashboard.data.room.ParentRestaurantCrossRef
import com.fpremake.screens_post_login.screen_dashboard.data.room.Restaurant

@Database(entities = [Child::class, Parent::class, Restaurant::class, ParentRestaurantCrossRef::class], version = 3, exportSchema = true)
abstract class UserRoomDatabase : RoomDatabase() {

    abstract fun getUserDao(): ChildDao

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