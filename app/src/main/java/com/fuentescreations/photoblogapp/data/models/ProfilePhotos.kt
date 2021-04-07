package com.fuentescreations.photoblogapp.data.models

import com.google.gson.annotations.SerializedName

data class ProfilePhotos(
    val id:Int=-1,
    val author:String="",
    val width:Int=-1,
    val height:Int=-1,
    @SerializedName("url")
    val originUrl:String="",
    @SerializedName("download_url")
    val imageUrl:String=""
)
