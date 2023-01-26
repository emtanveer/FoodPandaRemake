package com.fpremake.di.meme

import android.content.Context
import androidx.room.Room
import com.fpremake.screens_post_login.screen_dashboard.data.room.Child
import com.fpremake.shared.data.meme_repository.MemeRestClientApi
import com.fpremake.shared.data.room.ChildDao
import com.fpremake.shared.data.room.UserRoomDatabase
import com.fpremake.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MemeModule {
    private const val BASE_URL = Constants.BASE_URL

    @Singleton
    @Provides //From here Hilt will know i can get Rest Api for the Trending GitHub Repo
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): MemeRestClientApi = retrofit.create(MemeRestClientApi::class.java)

    @Singleton // Tell Dagger-Hilt to create a singleton accessible everywhere in Application Component (i.e. everywhere in the application)
    @Provides
    fun provideUserRoomDatabase(@ApplicationContext appContext: Context): UserRoomDatabase {
        return UserRoomDatabase.getDatabase(appContext)
//        return Room.databaseBuilder(
//            appContext,
//            UserRoomDatabase::class.java,
//            "user"
//        ).build()
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
