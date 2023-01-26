package com.fpremake.di.meme

import android.content.Context
import android.util.Log
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
import okhttp3.Call
import okhttp3.EventListener
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.ConnectException
import java.net.SocketException
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
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()

                // Customize the request
                val request = original.newBuilder()
                request.header("Content-Type", "application/x-www-form-urlencoded")

                var response: Response? = null
                try {
                    response = chain.proceed(request.build())
                    // Customize or return the response
                    response

                } catch (e: ConnectException) {
                    Log.e("RETROFIT", "ERROR : " + e.localizedMessage)
                    chain.proceed(original)
                } catch (e: SocketException) {
                    Log.e("RETROFIT", "ERROR : " + e.localizedMessage)
                    chain.proceed(original)
                } catch (e: IOException) {
                    Log.e("RETROFIT", "ERROR : " + e.localizedMessage)
                    chain.proceed(original)
                } catch (e: Exception) {
                    Log.e("RETROFIT", "ERROR : " + e.localizedMessage)
                    chain.proceed(original)
                }
            }
            .eventListener(object : EventListener() {
                override fun callFailed(call: Call, ioe: IOException) {
                    super.callFailed(call, ioe)
                }
            })
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .build()

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
