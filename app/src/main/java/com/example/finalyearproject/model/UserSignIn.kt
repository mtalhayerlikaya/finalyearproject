package com.example.finalyearproject.model

import com.google.gson.annotations.SerializedName

data class UserSignIn(

    @SerializedName("email")
    val email :String,
    @SerializedName("password")
    val password:String

)
