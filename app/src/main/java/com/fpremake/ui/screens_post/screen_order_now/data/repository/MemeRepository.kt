package com.fpremake.ui.screens_post.screen_order_now.data.repository

import com.fpremake.ui.screens_post.screen_order_now.business.MemeDetail
import kotlinx.coroutines.flow.Flow

interface MemeRepository {
    suspend fun getMemes(): Flow<ArrayList<MemeDetail>>
}