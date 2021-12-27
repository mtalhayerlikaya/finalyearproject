package com.example.finalyearproject.repository

import com.example.finalyearproject.Api.RetrofitApi
import com.example.finalyearproject.model.ResponseSignIn
import com.example.finalyearproject.model.ResponseSignUp
import com.example.finalyearproject.model.RequestSignIn
import com.example.finalyearproject.model.RequestSignUp
import com.example.finalyearproject.util.Resource
import com.google.gson.GsonBuilder
import javax.inject.Inject

class LoginPageRepository @Inject constructor(
    private val api :RetrofitApi
) {

    suspend fun signInRequest(requestSignIn: RequestSignIn) : ResponseSignIn {

        return try {
            val response = api.sendSignInRequest(requestSignIn)

            if(response.isSuccessful){
                response.body()?.let {
                    return@let it
                } ?: return ResponseSignIn("FAILED",null,null,"response is null")
            }else{
                val gson = GsonBuilder().create()
                val mError = gson.fromJson(response.errorBody()?.string(), ResponseSignIn::class.java)
                return ResponseSignIn("FAILED",null,null,mError.message)
            }

        }catch (e:Exception){
            ResponseSignIn("FAILED",null,null,e.localizedMessage.toString())

        }

    }





}