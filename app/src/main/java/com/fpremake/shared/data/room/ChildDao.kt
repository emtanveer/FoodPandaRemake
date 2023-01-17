package com.fpremake.shared.data.room

import androidx.room.*
import com.fpremake.screens_post_login.screen_dashboard.data.room.Child
import com.fpremake.screens_post_login.screen_dashboard.data.room.Parent
import com.fpremake.screens_post_login.screen_dashboard.data.room.ParentWithChildren
import kotlinx.coroutines.flow.Flow

@Dao
interface ChildDao {
    @Query("SELECT * FROM child ORDER BY firstName ASC")
    fun getAlphabetizedWords(): Flow<List<Child>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChild(user: Child)


//    @Delete
//    suspend fun deleteAll()

    @Query("SELECT * FROM child")
    suspend fun getAllChilds(): List<Child>

    @Query("Insert INTO parent (name) VALUES (:name, :child)")
    suspend fun insetChildIntoParent(name: String, child: Child)

    @Query("Select * FROM parent")
    suspend fun getChildFromParent(): List<Parent>


    //region one-to-many relationship test cases

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertParent(parent: Parent): Long

    @Insert
    fun insertChildren(children: List<Child>)

//    @Transaction
//    @Query("SELECT * FROM parent")
//    fun parentWithChildren(): List<ParentWithChildren>

    //endregion
}