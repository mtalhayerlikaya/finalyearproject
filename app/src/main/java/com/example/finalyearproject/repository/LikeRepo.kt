package com.example.finalyearproject.repository

import com.example.finalyearproject.Api.RetrofitApi
import com.example.finalyearproject.model.EmailResponse
import com.example.finalyearproject.model.LikeRequest
import com.example.finalyearproject.model.LikeResponse
import com.example.finalyearproject.util.Singleton
import com.google.gson.GsonBuilder
import javax.inject.Inject


class LikeRepo
@Inject
constructor(val api:RetrofitApi){

    suspend fun sendLikeAndUnlikeRequest(token : String,request:LikeRequest) : LikeResponse {
        return try {
            val response = api.sendRequesLikeAndUnlike(token,request)
            println(response)
            println(response.body())
            if(response.isSuccessful){
                response.body()?.let {
                    return@let it
                } ?: return LikeResponse("FAILED","an error occurred","null","an error occurred")
            }
            else{
                val gson = GsonBuilder().create()
                val mError = gson.fromJson(response.errorBody()?.string(), EmailResponse::class.java)
                return LikeResponse("FAILED","null","null",mError.message.toString())
            }

        }
        catch (e:Exception){
            return LikeResponse("FAILED", "null","null","an error occurred")

        }
    }




}