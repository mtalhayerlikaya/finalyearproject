package com.example.finalyearproject.DependencyInjection

import com.example.finalyearproject.Api.RetrofitApi
import com.example.finalyearproject.util.Url
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MyApplication {

    @Singleton
    @Provides
    fun provideAnalyticsService(
        // Potential dependencies of this type
    ): RetrofitApi {
        return  Retrofit.Builder()
            .baseUrl(Url.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitApi::class.java)
    }

}