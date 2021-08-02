package com.example.photooftheday.model.rest

import com.example.photooftheday.model.data.PODServerResponseData
import com.example.photooftheday.model.rest.utils.ApiUtils
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PodApiRepositoryImpl {
    private val api: PodApi.PictureOfTheDayApi by lazy {
        val adapter = Retrofit.Builder()
            .baseUrl(ApiUtils.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(ApiUtils.getOkHTTPBuilderWithHeaders())
            .build()

        adapter.create(PodApi.PictureOfTheDayApi::class.java)
    }

    fun getPictureOfTheDay(callback : Callback<PODServerResponseData>){
        api.getPictureOfTheDay().enqueue(callback)
    }
}