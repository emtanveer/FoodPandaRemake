package com.fpremake


import android.util.Log
import com.fpremake.di.hilt.RealmModule
import com.fpremake.screens_post_login.screen_dashboard.data.Child
import com.fpremake.screens_post_login.screen_dashboard.data.Parent
import com.fpremake.screens_post_login.screen_dashboard.data.User
import com.fpremake.shared.Emojis
import com.fpremake.shared.data.realm.DataSource
import com.fpremake.shared.data.realm.UserRealmRepository
import com.fpremake.shared.data.realm.UserRealmRepositoryImpl
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.realm.kotlin.Configuration
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmQuery
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.Assert.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ExampleUnitTest {
    companion object {
        //Mock data
        fun buildUser() = User().apply {
            this.firstName = "Tanveerrr"
            this.isComplete = true
            this.father_id = "2201"
            this.emoji = Emojis.emojis?.get(5) ?: ""
        }

        const val TAG = "RealmUnitTesting"
    }

    private val dataSource = mockk<DataSource>(relaxed = true)
//    private val realmConfigurationBuilder = RealmConfiguration.Builder(schema = setOf(User::class, Parent::class, Child::class)).name("alternate-realm-test").compactOnLaunch()
//    private val realmConfiguration = realmConfigurationBuilder.build()


//    private val mockRealm = Realm.open(configuration = realmConfiguration)
// create the mocked realm


    private val realmModule = mockk<RealmModule>(relaxed = true)
//    private val mockRealm = realmModule.provideRealmInstance(realmModule.provideRealmConfig())
    private val mockRealm = mockk<Realm>(relaxed = true)
    private val sut = UserRealmRepositoryImpl(dataSource, mockRealm)

    //private val realm: Realm = realmInstance.provideRealmInstance(realmInstance.provideTestRealmConfig())
    //var sut: UserRealmRepositoryImpl = UserRealmRepositoryImpl(dataSource, mockRealm)

    @Before
    fun init() {
        //REALM OBJECT WITH CONFIG
        //every { Realm.open(mockRealmConfig) } returns mockRealm

        //region test config
        // :snippet-start: configure-a-realm-local
        //        val config = RealmConfiguration.Builder(
        //            schema =
        //            setOf(
        //                User::class,
        //                Parent::class,
        //                Child::class
        //            )
        //        )
        //            .name("alternate-realm-test")
        //            .compactOnLaunch()
        //            .build()
        //
        //        Realm.open(config)


        //       every { Realm.open(configuration = config) } returns mockRealm
        //        every { mockRealmProvider.get() } returns Observable.just(mockRealm)

        //endregion
    }

    @After
    fun tearDown() {
        //To close the Realm connection
        mockRealm.close()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `verify correct username is retrieved`() = runTest {
        val user = buildUser()
        var nameFromDB = ""

        // GETTING ME A MOCK USER DEFINED IN THE TOP OF THE CLASS.
        //mockito -> `when`(dataSource.get(any())).then { buildUser() }
        //mockK
        coEvery { dataSource.get(any()) } returns buildUser()


//        usersResults.forEachIndexed { index, user ->
////          Log.e(TAG, "user # $index, ${user},${user.firstName},${user.father_id},${user.emoji}")
//            nameFromDB = user.toString()
//        }




//coEvery {
//            val usersResults = sut.getFromRealm(0)
//            usersResults.forEachIndexed { index, user ->
//            // Log.e(TAG, "user # $index, ${user._id},${user.firstName},${user.father_id},${user.emoji}")
//
//                nameFromDB = ((user as User).firstName)
//            }

        mockRealm.writeBlocking {
            sut.createOrAddUserInRealm(user)
            //val usersResults = mockRealm.query<User>().find()
//            val usersResults= sut.getFromRealm(0)
//
//            usersResults.forEachIndexed { index, user ->
//                nameFromDB = (user as User).firstName
//            }

        }
       // }

        assertEquals(user.firstName, nameFromDB)

//            try {
//                realm.writeBlocking {
//                    val result = this.query<User>().find()
//
//                    result.forEachIndexed { index, user ->
//                       // Log.e(TAG, "user # $index, ${user._id},${user.firstName},${user.father_id},${user.emoji}")
//
//
//                    }
//                    Assert.assertEquals(user.firstName, name)
//                    Assert.assertEquals(user.firstName, name)
//                }
//            } catch (e: IllegalAccessException) {
//                Log.e(TAG, e.printStackTrace().toString())
//            }
    }

}