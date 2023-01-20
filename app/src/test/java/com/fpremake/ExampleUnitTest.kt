package com.fpremake


import android.util.Log
import com.fpremake.di.hilt.RealmModule
import com.fpremake.screens_post_login.screen_dashboard.data.realm.Child
import com.fpremake.screens_post_login.screen_dashboard.data.realm.Parent
import com.fpremake.screens_post_login.screen_dashboard.data.realm.User
import com.fpremake.shared.Emojis
import com.fpremake.shared.data.realm.DataSource
import com.fpremake.shared.data.realm.UserRealmRepositoryImpl
import io.mockk.*
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.Assert.*


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
    private val realmModule = mockk<RealmModule>(relaxed = true)

    //    private var realm: Realm? = mockk<Realm>(relaxed = false)
    private var realmConfiguration: RealmConfiguration = mockk<RealmConfiguration>(relaxed = true)
    private var realm: Realm? = mockk<Realm>()
    private var interactor: UserRealmRepositoryImpl? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        realmConfiguration = RealmConfiguration.Builder(
            schema =
            setOf(
                User::class,
                Parent::class,
                Child::class
            )
        ).apply {
            this.name("alternate-realm-test")
            this.compactOnLaunch()
        }.build()

        //realm = Realm.open(realmConfiguration)
        interactor = UserRealmRepositoryImpl(dataSource, realm!!)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        realm!!.close()
    }
    //private val sut = UserRealmRepositoryImpl(dataSource, realm)


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `verify correct username is retrieved`() = runTest {
        val userExpected = buildUser()
        var nameFromDBActual = ""

        // GETTING ME A MOCK USER DEFINED IN THE TOP OF THE CLASS.
        //mockK
        //val userExpected = coEvery { dataSource.get(any()) } returns buildUser()

        realm?.writeBlocking {
            interactor?.createOrAddUserInRealm(userExpected)
            
            // all items in the realm
            val items: RealmResults<User> =
                realm?.query<User>()!!.find()

            items.forEachIndexed { index, user ->
                Log.e(
                    "realm",
                    "user # $index, ${user._id},${user.firstName},${user.father_id},${user.emoji}"
                )
                nameFromDBActual = user.firstName
            }


            assertEquals(userExpected, nameFromDBActual)
        }

    }

}