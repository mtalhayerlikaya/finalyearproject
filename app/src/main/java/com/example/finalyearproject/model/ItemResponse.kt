package com.example.finalyearproject.model

import java.io.Serializable

data class ItemResponse(
    val `data`: List<ItemsData>?=null,
    val status_code: String
):Serializable