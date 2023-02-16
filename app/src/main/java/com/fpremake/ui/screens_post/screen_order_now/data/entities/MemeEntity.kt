package com.fpremake.ui.screens_post.screen_order_now.data.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

//This data class Entity which as we discussed is the representation of how Meme data is represented by the Backend
@Parcelize
data class MemeEntity(
    var success: Boolean? = false,
    val data: DataEntity? = null
) : Parcelable {

}

@Parcelize
data class DataEntity(
    val memes: ArrayList<MemeDetailEntity>?
) : Parcelable {

}

@Parcelize
data class MemeDetailEntity(
    val id: String? = "",
    @SerializedName("name") val first_name: String? = "",
    val url: String? = "",
    val width: Int? = 0,
    @SerializedName("height") val users_height: Int? = 0,
    val box_count: Int? = 0,
    val captions: Int? = 0,
) : Parcelable {
}
