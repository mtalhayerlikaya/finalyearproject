package com.example.finalyearproject.model

import com.google.gson.annotations.SerializedName

data class UserSignUp(

    @SerializedName("name")
    val name:String,

    @SerializedName("dateOfBirth")
    val dateOfBirth:String,

    @SerializedName("email")
    val email :String,

    @SerializedName("password")
    val password:String,


)
