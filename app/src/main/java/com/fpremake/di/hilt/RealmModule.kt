package com.fpremake.di.hilt

import com.fpremake.screens_post_login.screen_dashboard.data.Child
import com.fpremake.screens_post_login.screen_dashboard.data.Parent
import com.fpremake.screens_post_login.screen_dashboard.data.User
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RealmModule {

    @Provides
    @Singleton
    fun provideRealmInstance(realmConfig: RealmConfiguration): Realm {
        return Realm.open(realmConfig)
    }

    @Provides
    @Singleton
    fun provideRealmInstance(): RealmConfiguration {
        val config = RealmConfiguration.Builder(
            schema =
            setOf(
                User::class,
                Parent::class,
                Child::class
            )
        ).apply {
            this.schemaVersion(1)
            this.deleteRealmIfMigrationNeeded()
            this.name("FoodPandaRealmConfig")
        }

        return config.build()

    }

}
