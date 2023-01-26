package com.fpremake.screens_post_login.screen_order_now.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

//This data class Entity which as we discussed is the representation of how Meme data is represented by the Backend
@Parcelize
data class MemeEntity(
    @SerializedName("success") var success: Boolean? = false,
    @SerializedName("data") val data: DataEntity? = null
) : Parcelable {

}

@Parcelize
data class DataEntity(
    @SerializedName("memes") val memes: ArrayList<MemeDetailEntity>?
) : Parcelable {

}

@Parcelize
data class MemeDetailEntity(
    @SerializedName("id") val id: String? = "",
    @SerializedName("name") val name: String? = "",
    @SerializedName("url") val url: String? = "",
    @SerializedName("width") val width: Int? = 0,
    @SerializedName("height") val height: Int? = 0,
    @SerializedName("box_count") val box_count: Int? = 0,
    @SerializedName("captions") val captions: Int? = 0,
) : Parcelable {
}
