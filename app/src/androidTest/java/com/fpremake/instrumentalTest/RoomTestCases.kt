package com.fpremake.instrumentalTest

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fpremake.shared.data.room.ChildDao
import com.fpremake.shared.data.room.UserRoomDatabase
import com.fpremake.ui.screens_post.screen_dashboard.data.room.*
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class RoomTestCases {

    private lateinit var childDao: ChildDao
    private lateinit var userRoomDatabase: UserRoomDatabase

    @Before
    fun initRoomDatabase() {
        //Init dao and user database
        val context = ApplicationProvider.getApplicationContext<Context>()
        userRoomDatabase =
            Room.inMemoryDatabaseBuilder(context, UserRoomDatabase::class.java).build()

        childDao = userRoomDatabase.getUserDao()
    }

    @After
    @Throws(IOException::class)
    fun dispose() {
        userRoomDatabase.close()
    }

    @Test
    fun insertUser_returnTrue() {
        val user1 = Child(firstName = "Tanveer")
        val user2 = Child(firstName = "Sharik")
        val user3 = Child(firstName = "Ali")
        runBlocking {
            childDao.insertChild(user1)
            childDao.insertChild(user2)
            childDao.insertChild(user3)
//            val users = childDao.getAllUsers()
//            Log.e("room","room: ${users.size}")
        }
        //Assert.assertNull(users)
    }

    @Test
    fun updateChild_returnTrue() {
        val children: ArrayList<Child> = ArrayList()
        children.add(Child(firstName = "Tanveer KHAN"))
        children.add(Child(firstName = "Sharik KHAN"))
        children.add(Child(firstName = "Stinger NOOB"))

        val parent1 = Parent(
            name = "Parent to update",
        )

        runBlocking {
            val parentWithChildren = ParentWithChildren(parent1, children)
            val parentId = childDao.insertParent(parentWithChildren.parent).toInt()
            for (child in parentWithChildren.children) {
                child.id_fkparent = parentId
            }
            childDao.insertChildren(parentWithChildren.children)


           // parentWithChildren.children[2].firstName = "SHARsdasdasdasIK"
            //val updatedChild = parentWithChildren.children[1]
            parentWithChildren.children.forEach {
                Log.e("room", "parent - Children: ${it.firstName}")
                it.id_fkparent = parentId
            }

          //  childDao.insertChildren(parentWithChildren.children)

            childDao.updateChildren("SHARsdasdasdasIK",2)
            val childrens = childDao.getChildAgainstParentOne(1)
           // Log.e("room", "parent updated Children: ${childrens.size}")
            childrens.forEachIndexed { index, child ->
                Log.e("room", "Parent # 1 -> child # ${index}: ${child.firstName} ${child.childId}")
            }

        }
        //Assert.assertNull(users)
    }


    //region one-to-many relationship test cases
    @Test
    fun insertChildIntoParent_returnTrue() {
        val parent1 = Parent(
            name = "Parent 1",
        )
        val parent2 = Parent(
            name = "Parent 2",
        )

        val children: ArrayList<Child> = ArrayList()
        children.add(Child(firstName = "Tanveer"))
        children.add(Child(firstName = "Sharik"))
        children.add(Child(firstName = "Stinger"))

        val childrenOfParent2: ArrayList<Child> = ArrayList()
        childrenOfParent2.add(Child(firstName = "Mage"))

        //val dbParent = childDao.insertParent(parent1)
        val parentOneWithChildren = ParentWithChildren(Parent(name = parent1.name), children)
        val parentTwoWithChildren =
            ParentWithChildren(Parent(name = parent2.name), childrenOfParent2)

        runBlocking {
            val identifierForParent1 = childDao.insertParent(parentOneWithChildren.parent)
            val identifierForParent2 = childDao.insertParent(parentTwoWithChildren.parent)

            for (child in parentOneWithChildren.children) {
                child.id_fkparent = identifierForParent1.toInt()    //This identifierForParent1.toInt() return the primary key of newly inserted long
            }

            for (child in parentTwoWithChildren.children) {
                child.id_fkparent = identifierForParent2.toInt()
            }


            childDao.insertChildren(parentOneWithChildren.children)
            childDao.insertChildren(parentTwoWithChildren.children)

            Log.e("room", "parent # 1: $parentOneWithChildren")
            Log.e("room", "parent # 2: $parentTwoWithChildren")


            parentOneWithChildren.children.forEachIndexed { index, child ->
                Log.e("room", "Parent # 1 -> child # ${index}: ${child.firstName}")
            }
            parentTwoWithChildren.children.forEachIndexed { index, child ->
                Log.e("room", "Parent # 2 -> child # ${index}: ${child.firstName}")
            }

        }
    }
    //endregion

    @Test
    fun getParentAndRestaurants() {
        val parent1 = Parent(name = "Sharik")
        val parent2 = Parent(name = "Ali")
        val parent3 = Parent(name = "Tanveer")

        val parent1ID = childDao.insertParent(parent1).toInt()
        val parent2ID = childDao.insertParent(parent2).toInt()
        val parent3ID = childDao.insertParent(parent3).toInt()

        val restaurant1 = Restaurant(restaurantName = "Restaurant 1")
        val restaurant2 = Restaurant(restaurantName = "Restaurant 2")
        val restaurant3 = Restaurant(restaurantName = "Restaurant 3")

        val restaurant1ID = childDao.insertRestaurant(restaurant1).toInt()
        val restaurant2ID = childDao.insertRestaurant(restaurant2).toInt()
        val restaurant3ID = childDao.insertRestaurant(restaurant3).toInt()

        childDao.insertParentRestaurantRelation(ParentRestaurantCrossRef(restaurant1ID,parent1ID))
        childDao.insertParentRestaurantRelation(ParentRestaurantCrossRef(restaurant1ID,parent2ID))
        childDao.insertParentRestaurantRelation(ParentRestaurantCrossRef(restaurant1ID,parent3ID))

        childDao.insertParentRestaurantRelation(ParentRestaurantCrossRef(restaurant2ID,parent1ID))
        childDao.insertParentRestaurantRelation(ParentRestaurantCrossRef(restaurant2ID,parent2ID))
        childDao.insertParentRestaurantRelation(ParentRestaurantCrossRef(restaurant2ID,parent3ID))

        childDao.insertParentRestaurantRelation(ParentRestaurantCrossRef(restaurant3ID,parent1ID))
        childDao.insertParentRestaurantRelation(ParentRestaurantCrossRef(restaurant3ID,parent2ID))
        childDao.insertParentRestaurantRelation(ParentRestaurantCrossRef(restaurant3ID,parent3ID))

        val parentWithRestaurants = childDao.getParentWithRestaurants()

        parentWithRestaurants.forEach {
            Log.e("room", "Parent -> Parent Id: {${it.parent.parentId}} - Parent Name: ${it.parent.name}")

            it.restaurant.forEach {restaurant ->
                Log.e("room", "Restaurant -> Restaurant Id: {${restaurant.restaurantId}} - Restaurant Name: ${restaurant.restaurantName}")
            }
        }
    }
}
