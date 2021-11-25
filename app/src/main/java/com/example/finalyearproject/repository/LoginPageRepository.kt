package com.example.finalyearproject.repository

import com.example.finalyearproject.Api.RetrofitApi
import com.example.finalyearproject.model.SignIn
import com.example.finalyearproject.model.SignUp
import com.example.finalyearproject.model.UserSignIn
import com.example.finalyearproject.model.UserSignUp
import com.example.finalyearproject.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class LoginPageRepository @Inject constructor(
    private val api :RetrofitApi
) {
    fun deneme(){
        println("bu bir denemedir")
    }

    suspend fun signInRequest(userSignIn: UserSignIn) : Resource<SignIn> {

        return try {
            val response = api.sendSignInRequest(userSignIn)
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

     suspend fun signUpRequest(userSignUp: UserSignUp) :Resource<SignUp>{

         return try {
             val response = api.sendSignUpRequest(userSignUp)
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