package com.example.finalyearproject.repository

import com.example.finalyearproject.Api.RetrofitApi
import com.example.finalyearproject.model.ItemResponse
import com.example.finalyearproject.model.ItemsData
import com.example.finalyearproject.model.ResponseSignUp
import com.google.gson.GsonBuilder
import javax.inject.Inject

class RepositoryForTabs
@Inject
constructor(val api:RetrofitApi) {


    suspend fun getItems(): ItemResponse{

        val response = api.sendRequestToGetItems()
        return try {
            if(response.isSuccessful){
                response.body()?.let {
                    return@let it
                } ?: ItemResponse(null, "FAILED")
            }else{
                return ItemResponse(null, "FAILED")
            }

        }catch (e:Exception){
            return ItemResponse(null, "FAILED")
        }
    }



}