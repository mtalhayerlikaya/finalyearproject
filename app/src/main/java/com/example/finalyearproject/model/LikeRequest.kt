package com.example.finalyearproject.model

import com.google.gson.annotations.SerializedName

data class LikeRequest(
    @SerializedName("item")
    val item:String
)
