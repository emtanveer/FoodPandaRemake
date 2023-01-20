package com.fpremake.shared.data.room

import androidx.room.*
import com.fpremake.screens_post_login.screen_dashboard.data.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ChildDao {
    @Query("SELECT * FROM child ORDER BY firstName ASC")
    fun getAlphabetizedWords(): Flow<List<Child>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChild(user: Child)

//    @Delete
//    suspend fun deleteAll()

    @Query("UPDATE child set firstName = :childName where childId = :index")
    fun updateChildren(childName: String, index: Int)

    @Insert
    fun insertChildren(children: List<Child>)

    @Query("SELECT * from child inner join parent on parent.parentId = child.id_fkparent where child.id_fkparent = :parentId")
    suspend fun getChildAgainstParentOne(parentId: Int): List<Child>

//    @Query("Insert INTO parent (name) VALUES (:name, :child)")
//    suspend fun insertChildIntoParent(name: String, child: Child)
//
//    @Query("Select * FROM parent")
//    suspend fun getChildFromParent(): List<Parent>


    //region one-to-many relationship test cases

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertParent(parent: Parent): Long



//    @Transaction
//    @Query("SELECT * FROM parent")
//    fun parentWithChildren(): List<ParentWithChildren>


    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRestaurant(restaurant: Restaurant) : Long

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertParentRestaurantRelation(parentRestaurantCrossRef: ParentRestaurantCrossRef)

    @Query("SELECT * FROM parent")
    fun getParentWithRestaurants() : List<ParentWithRestaurants>

    @Query("SELECT * FROM restaurant")
    fun getRestaurantWithParents() : List<RestaurantWithParents>

    @Query("SELECT * FROM parent WHERE parentId = :parentId")
    fun getParentWithRestaurantsById(parentId: Int) : ParentWithRestaurants

    @Query("SELECT * FROM restaurant WHERE restaurantId = :restaurantId ")
    fun getRestaurantWithParentsById(restaurantId: Int) : RestaurantWithParents

    //endregion
}