package com.example.photooftheday.model.rest

import com.example.photooftheday.model.data.PODServerResponseData
import com.example.photooftheday.model.rest.utils.ApiUtils
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

    fun getPictureOfTheDay(date : String,callback: Callback<PODServerResponseData>) {
        api.getPictureOfTheDay(date).enqueue(callback)
    }
}