package com.example.finalyearproject.repository

import com.example.finalyearproject.Api.RetrofitApi
import com.example.finalyearproject.model.EmailRequest
import com.example.finalyearproject.model.EmailResponse
import com.example.finalyearproject.util.Resource
import javax.inject.Inject

class ContinuePageRepo
@Inject
constructor(val api:RetrofitApi) {


   suspend fun sendEmail(request : EmailRequest) : EmailResponse {
        return try {
            val response = api.sendEmailToReset(request)
            println(response)
            println(response.body())
            if(response.isSuccessful){
                response.body()?.let {
                    return@let it
                } ?: return EmailResponse("FAILED","response is null","null","null")
            }
            else{
               return EmailResponse("FAILED","response is not successful","null","null")
            }

       }
    catch (e:Exception){
        return EmailResponse("FAILED",e.localizedMessage.toString(),"null","null")

        }
    }




}