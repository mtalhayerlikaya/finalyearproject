package com.example.finalyearproject.model

import com.google.gson.annotations.SerializedName

data class EmailResponse(


    @SerializedName("status_code")
    val status_code : String,
    @SerializedName("data")
    val data : String?=null,
    @SerializedName("type")
    val type : String?=null,
    @SerializedName("message")
    val message : String?=null


)