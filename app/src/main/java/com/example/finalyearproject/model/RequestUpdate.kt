package com.example.finalyearproject.model

import com.google.gson.annotations.SerializedName

data class RequestUpdate(

     @SerializedName("fullName")
     val full_name:String?=null,
     @SerializedName("password")
     val password:String?=null


)
