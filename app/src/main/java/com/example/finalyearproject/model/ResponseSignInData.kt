package com.example.finalyearproject.model

data class ResponseSignInData(
    val user_id:String,
    val full_name:String,
    val email:String,
    val verified:Boolean,
    val token:String
)