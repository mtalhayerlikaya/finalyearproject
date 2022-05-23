package com.example.finalyearproject.repository

import androidx.lifecycle.MutableLiveData
import com.example.finalyearproject.Api.RetrofitApi
import com.example.finalyearproject.model.UpdateRequest
import com.example.finalyearproject.model.UpdateResponse
import javax.inject.Inject

class UpdateRepository
@Inject constructor(val api:RetrofitApi)
{

    val updateLiveData = MutableLiveData<UpdateResponse?>()

    fun returnRepoLiveData():MutableLiveData<UpdateResponse?>{
        return updateLiveData
    }

    suspend fun sendUpdateRequest(updateRequest:UpdateRequest){

        val response = api.sendUpdateRequest(updateRequest)
        if(response.isSuccessful){
            response.body()?.let {
                println(response)
                println(it.status_code)
                updateLiveData.value = it
            }
        }

    }


}