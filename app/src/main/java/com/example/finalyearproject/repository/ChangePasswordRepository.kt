package com.example.finalyearproject.repository

import com.example.finalyearproject.Api.RetrofitApi
import com.example.finalyearproject.model.EmailResponse
import com.example.finalyearproject.model.RequestUpdate
import com.example.finalyearproject.model.ResponseUpdate
import com.google.gson.GsonBuilder
import javax.inject.Inject

class ChangePasswordRepository
@Inject
constructor(val api : RetrofitApi) {

    suspend fun resetPassword(token:String,request:RequestUpdate) : ResponseUpdate {
        return try {
            val response = api.sendToUpdate(request,token)
            if(response.isSuccessful){
                response.body()?.let {
                    return@let it
                } ?: return ResponseUpdate("FAILED","response is null","null","response is null")
            }
            else{
                val gson = GsonBuilder().create()
                val mError = gson.fromJson(response.errorBody()?.string(), ResponseUpdate::class.java)
                return ResponseUpdate("FAILED","Token is wrong","null",mError.message)
            }

        }
        catch (e:Exception){
            return ResponseUpdate("FAILED",e.localizedMessage.toString(),"null","null")

        }
    }



}