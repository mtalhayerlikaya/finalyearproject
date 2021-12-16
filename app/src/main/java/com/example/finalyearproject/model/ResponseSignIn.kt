package com.example.finalyearproject.model

import com.google.gson.annotations.SerializedName

data class ResponseSignIn(
    @SerializedName("status_code")
    val status: String,
    @SerializedName("data")
    val data: ResponseSignInData?=null,
    @SerializedName("type")
    val type:String?=null,
    @SerializedName("message")
    val message :String?=null

)