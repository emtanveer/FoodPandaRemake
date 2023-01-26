/*
package com.fpremake.shared.data.meme_repository

import android.util.Log
import com.fpremake.utils.Constants
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

*/
/**
 * Get retrofit client
 *
 * @return  Retrofit Client for Trending GitHub Repos.
 *//*


object RetrofitInstance {
    //region Helper method for Retrofit object

    fun getRetrofitClient(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()

                // Customize the request
                val request = original.newBuilder()
                request.header("Content-Type", "application/x-www-form-urlencoded")

                var response: Response? = null
                try {
                    response = chain.proceed(request.build())
                    response.cacheResponse()

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
            //            .cache(cache)
            .eventListener(object : EventListener() {
                override fun callFailed(call: Call, ioe: IOException) {
                    super.callFailed(call, ioe)
                }

            })
            .addInterceptor(interceptor)
            .connectTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .build()
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
    //endregion
}*/
