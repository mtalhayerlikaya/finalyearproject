package com.example.finalyearproject.Api

import com.example.finalyearproject.model.*
import retrofit2.Response
import retrofit2.http.*

interface RetrofitApi {

    @Headers("Content-Type: application/json")
    @POST("api/session/signin")
    suspend fun sendSignInRequest(@Body request: RequestSignIn): Response<ResponseSignIn>

    @Headers("Content-Type: application/json")
    @POST("api/session")
    suspend fun sendSignUpRequest(@Body request: RequestSignUp):Response<ResponseSignUp>

    @Headers("Content-Type: application/json")
    @POST("api/session/changepasswordrequest")
    suspend fun sendEmailToReset(@Body request: EmailRequest):Response<EmailResponse>

    @Headers("Content-Type: application/json")
    @POST("api/session/changepassword/{token}")
    suspend fun sendResetRequest(@Body request: RequestChangePassword,
        @Path(value = "token")  token:String
    ):Response<ResponseChangePassword>



}