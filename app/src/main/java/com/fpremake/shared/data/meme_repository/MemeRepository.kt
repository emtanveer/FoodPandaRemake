package com.fpremake.shared.data.meme_repository

import com.fpremake.screens_post_login.screen_order_now.data.MemeEntity
import kotlinx.coroutines.flow.Flow

interface MemeRepository {
    suspend fun getMemes(): Flow<MemeEntity>
}