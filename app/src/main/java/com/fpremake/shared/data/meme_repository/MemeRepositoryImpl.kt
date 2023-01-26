package com.fpremake.shared.data.meme_repository

import com.fpremake.screens_post_login.screen_order_now.data.MemeEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class MemeRepositoryImpl @Inject constructor(private val memeRestClientApi: MemeRestClientApi) : MemeRepository {
    override suspend fun getMemes(): Flow<MemeEntity> {
        return flowOf(memeRestClientApi.getAllMemes())
    }
}