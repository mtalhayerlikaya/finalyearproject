package com.example.finalyearproject.model

import com.google.gson.annotations.SerializedName

data class RequestChangePassword(

    @SerializedName("token")
    val token : String,
    @SerializedName("newPassword")
    val newPassword : String


)
