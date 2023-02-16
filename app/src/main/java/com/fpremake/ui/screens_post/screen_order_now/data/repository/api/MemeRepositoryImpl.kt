package com.fpremake.ui.screens_post.screen_order_now.data.repository.api

import android.util.Log
import com.fpremake.ui.screens_post.screen_order_now.business.MemeDetail
import com.fpremake.ui.screens_post.screen_order_now.data.repository.MemeRepository
import com.fpremake.utils.network_utils.NoConnectivityException
import com.fpremake.utils.network_utils.NoInternetException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class MemeRepositoryImpl @Inject constructor(private val memeRestClientApi: MemeRestClientApi) :
    MemeRepository {
    override suspend fun getMemes(): Flow<ArrayList<MemeDetail>> {
        return try {
            val memesFlow = (memeRestClientApi.getAllMemes().data?.memes?.map { memeDetailEntity ->
                memeDetailEntity.let {memeDetailEntity->
                    MemeDetail(
                        id = memeDetailEntity.id!!,
                        name = memeDetailEntity.first_name!!,
                        url = memeDetailEntity.url!!,
                        width = memeDetailEntity.width!!,
                        height = memeDetailEntity.users_height!!,
                        boxCount = memeDetailEntity.box_count!!,
                        captions = memeDetailEntity.captions!!
                    )
                }
            }) as ArrayList
            flowOf(memesFlow)
        } catch (e: NoConnectivityException) {
            Log.e("NoConnectivityException", e.message)
            flow { }
        } catch (e: NoInternetException) {
            Log.e("NoInternetException", e.message)
            flow { }
        } catch (e: Exception) {
            Log.e("Exception", e.message ?: "Unknown Exception")
            flow { }
        }
    }


}