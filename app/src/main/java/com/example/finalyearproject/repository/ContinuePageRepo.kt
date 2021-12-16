package com.example.finalyearproject.repository

import com.example.finalyearproject.Api.RetrofitApi
import com.example.finalyearproject.model.EmailRequest
import com.example.finalyearproject.model.EmailResponse
import com.example.finalyearproject.model.ResponseSignIn
import com.example.finalyearproject.util.Resource
import com.google.gson.GsonBuilder
import javax.inject.Inject

class ContinuePageRepo
@Inject
constructor(val api:RetrofitApi) {


   suspend fun sendEmail(request : EmailRequest) : EmailResponse {
        return try {
            val response = api.sendEmailToReset(request.email)
            println("after this")
            println(response)
            println(response.body())
            if(response.isSuccessful){
                response.body()?.let {
                    return@let it
                } ?: return EmailResponse("FAILED","data is null","null","box is emty")
            }
            else{
                val gson = GsonBuilder().create()
                val mError = gson.fromJson(response.errorBody()?.string(), EmailResponse::class.java)
               return EmailResponse("FAILED","null","null",mError.message.toString())
            }

       }
    catch (e:Exception){
        return EmailResponse("FAILED", "null","null","this email does not exists")

        }
    }




}