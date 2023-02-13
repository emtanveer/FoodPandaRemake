package com.fpremake.di.meme

import android.content.Context
import com.fpremake.screens_post_login.screen_dashboard.data.room.Child
import com.fpremake.screens_post_login.screen_order_now.data.repository.MemeRepository
import com.fpremake.screens_post_login.screen_order_now.data.repository.api.MemeRepositoryImpl
import com.fpremake.screens_post_login.screen_order_now.data.repository.api.MemeRestClientApi
import com.fpremake.shared.data.room.ChildDao
import com.fpremake.shared.data.room.UserRoomDatabase
import com.fpremake.utils.Constants
import com.fpremake.utils.network_utils.NoConnectionInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
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
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        noConnectionInterceptor: NoConnectionInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(noConnectionInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .build()
    }

    @Singleton
    @Provides
    fun provideNoConnectionInterceptor(@ApplicationContext context: Context) : NoConnectionInterceptor {
        return NoConnectionInterceptor(context)
    }

    @Singleton
    @Provides
    fun provideMemeRepository(memeRestClientApi: MemeRestClientApi) : MemeRepository {
        return MemeRepositoryImpl(memeRestClientApi)
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): MemeRestClientApi =
        retrofit.create(MemeRestClientApi::class.java)

    @Singleton // Tell Dagger-Hilt to create a singleton accessible everywhere in Application Component (i.e. everywhere in the application)
    @Provides
    fun provideUserRoomDatabase(@ApplicationContext appContext: Context): UserRoomDatabase {
        return UserRoomDatabase.getDatabase(appContext)
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
