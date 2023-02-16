package com.fpremake.di.room

import android.content.Context
import androidx.room.Room
import com.fpremake.ui.screens_post.screen_dashboard.data.room.Child
import com.fpremake.shared.data.room.ChildDao
import com.fpremake.shared.data.room.UserRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideUserRoomDatabase(@ApplicationContext appContext: Context): UserRoomDatabase {
//        return UserRoomDatabase.getDatabase(appContext)
        return Room.databaseBuilder(
            appContext,
            UserRoomDatabase::class.java,
            "user"
        ).build()
    }

    //Dao
    @Provides
    @Singleton
    fun provideUserDao(userRoomDatabase: UserRoomDatabase): ChildDao {
        return userRoomDatabase.getUserDao()
    }

    @Provides
    fun provideUserEntity(): Child {
        return Child()
    }

}