package com.fpremake.screens_post_login.screen_order_now.presentation

import com.fpremake.screens_post_login.screen_order_now.data.MemeEntity

data class OrderNowViewState(
//    val items: ArrayList<MemeDetailEntity> = arrayListOf(),
    val items: MemeEntity = MemeEntity(), //Initialized with null values
    val state: PageState = PageState.Loading,
)

sealed class PageState {
    object Content : PageState()
    object Loading : PageState()
    object Error : PageState()
}



