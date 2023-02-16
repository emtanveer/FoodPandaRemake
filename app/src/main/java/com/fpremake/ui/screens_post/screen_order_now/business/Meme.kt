package com.fpremake.ui.screens_post.screen_order_now.business

//This belongs to Business Layer/Domain Layer
data class Meme(
    val success : Boolean,
    val data: Data
)
data class Data(
    val memes:ArrayList<MemeDetail>
)

data class MemeDetail(
    val id:String,
    val name:String,
    val url:String,
    val width:Int,
    val height:Int,
    val boxCount:Int,
    val captions:Int,
)
