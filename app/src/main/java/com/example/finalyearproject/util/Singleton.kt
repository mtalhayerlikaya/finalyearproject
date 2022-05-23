package com.example.finalyearproject.util

import com.example.finalyearproject.model.ItemsData
import com.example.finalyearproject.model.Product

object Singleton {

    var detailCardViewTitle:String?=null
    var detailCardViewPrice:String?=null
    var likedItems:ArrayList<String>?=null
    var rewardItems:ArrayList<ItemsData>?=null
    var basketItems:ArrayList<Product>?=null
    var items:List<ItemsData>?=null
    var selectedItemId:String?=null
    var token:String?=null
    var fullname :String?=null
}