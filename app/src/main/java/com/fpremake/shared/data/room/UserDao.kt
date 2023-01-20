package com.fpremake.shared.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fpremake.screens_post_login.screen_dashboard.data.room.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user ORDER BY firstName ASC")
    fun getAlphabetizedWords(): Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(user: User)

    @Query("DELETE FROM user")
    suspend fun deleteAll()
}