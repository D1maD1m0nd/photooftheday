package com.example.photooftheday.model.rest

import com.example.photooftheday.model.data.PODServerResponseData
import com.example.photooftheday.model.data.earth_data.Earth
import com.example.photooftheday.model.data.mars_data.MarsPhotos
import com.example.photooftheday.model.rest.utils.ApiUtils
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NasaApiRepositoryImpl {
    private val API: NasaApi by lazy {
        val adapter = Retrofit.Builder()
            .baseUrl(ApiUtils.BASE_URL_MAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .client(ApiUtils.getOkHTTPBuilderWithHeaders())
            .build()

        adapter.create(NasaApi::class.java)
    }

    fun getPictureOfTheDay(date: String, callback: Callback<PODServerResponseData>) {
        API.getPictureOfTheDay(date).enqueue(callback)
    }

    fun getEarthImages(date: String, callback: Callback<List<Earth>>) {
        API.getEarthImage(date).enqueue(callback)
    }

    fun getMarsImages(date: String, camera: String, callback: Callback<MarsPhotos>) {
        API.getMarsImage(date, camera).enqueue(callback)
    }
}