package com.fpremake.screens_post_login.screen_order_now.data.repository.api

import com.fpremake.screens_post_login.screen_order_now.data.entities.MemeEntity
import retrofit2.http.GET

interface MemeRestClientApi {
    @GET("get_memes")
    suspend fun getAllMemes(): MemeEntity

    //Using DI now, no need to provide below
//    companion object {
//        operator fun invoke(): MemeRestClientApi {
//            return RetrofitInstance.getRetrofitClient().create(MemeRestClientApi::class.java)
//        }
//    }
}