package com.example.finalyearproject.repository

import com.example.finalyearproject.Api.RetrofitApi
import com.example.finalyearproject.model.ResponseSignIn
import com.example.finalyearproject.model.ResponseSignUp
import com.example.finalyearproject.model.RequestSignIn
import com.example.finalyearproject.model.RequestSignUp
import com.example.finalyearproject.util.Resource
import javax.inject.Inject

class LoginPageRepository @Inject constructor(
    private val api :RetrofitApi
) {
    fun deneme(){
        println("bu bir denemedir")
    }

    suspend fun signInRequest(requestSignIn: RequestSignIn) : ResponseSignIn {

        return try {
            val response = api.sendSignInRequest(requestSignIn)
            println("insude sending")
            println(requestSignIn)
            println(response)
            println(response.body())
            if(response.isSuccessful){
                println(response)
                println(response.body())
                response.body()?.let {
                    return@let it
                } ?: return ResponseSignIn("FAILED",null,null,"response is null")
            }else{
                return ResponseSignIn("FAILED",null,null,"response is not succesfull")
            }

        }catch (e:Exception){
            ResponseSignIn("FAILED",null,null,"response is not succesfull")

        }

    }





}