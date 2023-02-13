package com.fpremake.screens_post_login.screen_order_now.data.repository

import com.fpremake.screens_post_login.screen_order_now.business.MemeDetail
import kotlinx.coroutines.flow.Flow

interface MemeRepository {
    suspend fun getMemes(): Flow<ArrayList<MemeDetail>>
}