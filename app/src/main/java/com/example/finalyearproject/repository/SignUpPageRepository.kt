package com.example.finalyearproject.repository

import com.example.finalyearproject.Api.RetrofitApi
import com.example.finalyearproject.model.RequestSignUp
import com.example.finalyearproject.model.ResponseSignIn
import com.example.finalyearproject.model.ResponseSignUp
import com.example.finalyearproject.util.Resource
import com.google.gson.GsonBuilder
import javax.inject.Inject

class SignUpPageRepository
@Inject
constructor(private val api :RetrofitApi) {

    suspend fun signUpRequest(requestSignUp: RequestSignUp) : ResponseSignUp {

        return try {
            val response = api.sendSignUpRequest(requestSignUp)

            if(response.isSuccessful){
                response.body()?.let {
                    return@let it
                } ?: return ResponseSignUp("FAILED",null,null,"response is null")
            }else{
                val gson = GsonBuilder().create()
                val mError = gson.fromJson(response.errorBody()?.string(), ResponseSignUp::class.java)
                return ResponseSignUp("FAILED",null,null,mError.message)
            }

        }catch (e:Exception){
            ResponseSignUp("FAILED",null,null,e.localizedMessage.toString())

        }

    }



}