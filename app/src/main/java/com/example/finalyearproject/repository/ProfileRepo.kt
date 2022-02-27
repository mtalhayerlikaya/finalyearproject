package com.example.finalyearproject.repository

import com.example.finalyearproject.Api.RetrofitApi
import com.example.finalyearproject.model.ItemResponse
import javax.inject.Inject

class ProfileRepo
@Inject
constructor(val api:RetrofitApi)
{

    suspend fun getItems(): ItemResponse {

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