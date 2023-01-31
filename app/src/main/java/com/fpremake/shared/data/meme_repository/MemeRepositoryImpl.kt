package com.fpremake.shared.data.meme_repository

import android.util.Log
import com.fpremake.screens_post_login.screen_order_now.data.MemeEntity
import com.fpremake.utils.network_interceptor_utils.NoConnectivityException
import com.fpremake.utils.network_interceptor_utils.NoInternetException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class MemeRepositoryImpl @Inject constructor(private val memeRestClientApi: MemeRestClientApi) : MemeRepository {
    override suspend fun getMemes(): Flow<MemeEntity> {
        return try {
            val memesFlow = memeRestClientApi.getAllMemes()
             flowOf(memesFlow)
        } catch (e: NoConnectivityException) {
            Log.e("NoConnectivityException", e.message)
            flow {  }
        } catch (e: NoInternetException) {
            Log.e("NoInternetException", e.message)
            flow {  }
        } catch (e: Exception) {
            Log.e("Exception", e.message ?: "Unknown Exception")
            flow {  }
        }
    }
}