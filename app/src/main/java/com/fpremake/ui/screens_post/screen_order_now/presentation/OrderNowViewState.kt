package com.fpremake.ui.screens_post.screen_order_now.presentation

import com.fpremake.ui.screens_post.screen_order_now.business.MemeDetail

data class OrderNowViewState(
    val items: ArrayList<MemeDetail> = arrayListOf(),
//    val items: MemeEntity? = MemeDetailEntity, //Initialized with null values
    val state: PageState = PageState.Content,
)

sealed class PageState {
    object Content : PageState()
    object Loading : PageState()
    object Error : PageState()
}



