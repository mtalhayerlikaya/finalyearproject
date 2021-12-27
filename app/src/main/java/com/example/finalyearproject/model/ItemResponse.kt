package com.example.finalyearproject.model

data class ItemResponse(
    val `data`: List<ItemsData>?=null,
    val status_code: String
)