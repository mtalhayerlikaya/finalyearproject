package com.example.finalyearproject.repository

import com.example.finalyearproject.Api.RetrofitApi
import com.example.finalyearproject.model.RequestSignUp
import com.example.finalyearproject.model.ResponseSignUp
import com.example.finalyearproject.util.Resource
import javax.inject.Inject

class SignUpPageRepository
@Inject
constructor(private val api :RetrofitApi) {



    suspend fun signUpRequest(requestSignUp: RequestSignUp) : Resource<ResponseSignUp> {

        return try {
            val response = api.sendSignUpRequest(requestSignUp)
            if(response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: return Resource.error(response.message(),null)
            }else{
                return Resource.error("request is not succesfull",null)
            }

        }catch (e:Exception){
            Resource.error(e.printStackTrace().toString(),null)
        }

    }



}