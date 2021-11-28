package com.example.finalyearproject.model

import com.google.gson.annotations.SerializedName

data class ResponseChangePassword(

    @SerializedName("status_code")
    val status_code : String,
    @SerializedName("data")
    val message : String,
    @SerializedName("type")
    val type : String,
    @SerializedName("message")
    val errorMessage : String




)
