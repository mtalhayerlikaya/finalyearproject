package com.example.finalyearproject.model

import com.google.gson.annotations.SerializedName

data class ResponseSignUpData(
    @SerializedName("user_id")
    val user_id:String,
    @SerializedName("full_name")
    val full_name:String,
    @SerializedName("email")
    val email:String,
    @SerializedName("verified")
    val verified:Boolean

)