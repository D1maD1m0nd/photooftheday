package com.example.photooftheday.model.rest

import com.example.photooftheday.model.data.PODServerResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PodApi {
    interface  PictureOfTheDayApi {
        @GET("apod")
        fun getPictureOfTheDay(): Call<PODServerResponseData>

    }
}