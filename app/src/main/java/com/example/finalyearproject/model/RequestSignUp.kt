package com.example.finalyearproject.model

import com.google.gson.annotations.SerializedName

data class RequestSignUp(

    @SerializedName("fullName")
    val full_name:String,
    @SerializedName("email")
    val email:String,
    @SerializedName("password")
    val password : String


)
