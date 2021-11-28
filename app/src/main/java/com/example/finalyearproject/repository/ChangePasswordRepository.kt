package com.example.finalyearproject.repository

import com.example.finalyearproject.Api.RetrofitApi
import com.example.finalyearproject.model.RequestChangePassword
import com.example.finalyearproject.model.ResponseChangePassword
import javax.inject.Inject

class ChangePasswordRepository
@Inject
constructor(val api : RetrofitApi) {

    suspend fun resetPassword(token:String,request : RequestChangePassword) : ResponseChangePassword {
        return try {
            val response = api.sendResetRequest(request,token)
            println(response)
            println()
            if(response.isSuccessful){
                response.body()?.let {
                    return@let it
                } ?: return ResponseChangePassword("FAILED","response is null","null","null")
            }
            else{
                return ResponseChangePassword("FAILED","response is not successful","null","null")
            }

        }
        catch (e:Exception){
            return ResponseChangePassword("FAILED",e.localizedMessage.toString(),"null","null")

        }
    }



}