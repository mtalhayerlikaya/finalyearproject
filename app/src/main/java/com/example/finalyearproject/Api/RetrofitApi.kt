package com.example.finalyearproject.Api

import com.example.finalyearproject.model.SignIn
import com.example.finalyearproject.model.SignUp
import com.example.finalyearproject.model.UserSignIn
import com.example.finalyearproject.model.UserSignUp
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetrofitApi {

    @Headers("Content-Type: application/json")
    @POST("user/signin")
    suspend fun sendSignInRequest(@Body user: UserSignIn): Response<SignIn>

    @Headers("Content-Type: application/json")
    @POST("user/signup")
    suspend fun sendSignUpRequest(@Body user: UserSignUp):Response<SignUp>



}