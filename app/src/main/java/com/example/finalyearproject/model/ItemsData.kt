package com.example.finalyearproject.model

import java.io.Serializable

data class ItemsData(
    val __v: Int,
    val _id: String,
    val price: Double,
    val title: String,
    val type: String,
    val url: String
):Serializable