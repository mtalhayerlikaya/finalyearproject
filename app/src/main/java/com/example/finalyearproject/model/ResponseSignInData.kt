package com.example.finalyearproject.model

import com.google.gson.annotations.SerializedName

data class ResponseSignInData(

    val fullName:String,
    val token:String,
    val likedItems :ArrayList<String>?=null
)