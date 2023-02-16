package com.fpremake.di.hilt

import com.fpremake.ui.screens_post.screen_dashboard.data.realm.Child
import com.fpremake.ui.screens_post.screen_dashboard.data.realm.Parent
import com.fpremake.ui.screens_post.screen_dashboard.data.realm.User
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RealmModule {

    @Provides
    @Singleton
    fun provideRealmInstance(realmConfig: RealmConfiguration): Realm {
        return Realm.open(realmConfig)
    }

    @Provides
    @Singleton
    fun provideRealmConfig(): RealmConfiguration {
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
