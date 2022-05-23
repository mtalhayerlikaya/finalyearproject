package com.example.finalyearproject.Api

import com.example.finalyearproject.model.*
import retrofit2.Response
import retrofit2.http.*

interface RetrofitApi {

    @Headers("Content-Type: application/json")
    @POST("api/users/login")
    suspend fun sendSignInRequest(@Body request: RequestSignIn): Response<ResponseSignIn>

    @Headers("Content-Type: application/json")
    @POST("api/users/signup")
    suspend fun sendSignUpRequest(@Body request: RequestSignUp):Response<ResponseSignUp>

    @Headers("Content-Type: application/json")
    @GET("api/users/token")
    suspend fun sendEmailToReset(
        @Query("email") email:String
    ):Response<EmailResponse>

    @Headers("Content-Type: application/json")
    @POST("api/users/update")
    suspend fun sendToUpdate(
        @Body request: RequestUpdate,
        @Query("token") token:String
    ):Response<ResponseUpdate>


    @GET("api/items")
    suspend fun sendRequestToGetItems():Response<ItemResponse>

    @Headers("Content-Type: application/json")
    @POST("api/users/like")
    suspend fun sendRequesLikeAndUnlike(@Query("token") token:String,@Body request: LikeRequest):Response<LikeResponse>

    @Headers("Content-Type: application/json")
    @POST("api/users/update")
    suspend fun sendUpdateRequest(@Body updateRequest:UpdateRequest):Response<UpdateResponse>




}