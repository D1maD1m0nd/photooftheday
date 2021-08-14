package com.example.photooftheday.model.rest

import com.example.photooftheday.model.data.PODServerResponseData
import com.example.photooftheday.model.data.earth_data.Earth
import com.example.photooftheday.model.data.mars_data.MarsPhotos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApi {

    @GET("planetary/apod")
    fun getPictureOfTheDay(
        @Query("date") page: String
    ): Call<PODServerResponseData>

    @GET("EPIC/api/natural")
    fun getEarthImage(
        @Query("date") date: String
    ): Call<List<Earth>>

    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    fun getMarsImage(
        @Query("earth_date") date: String,
        @Query("camera") camera: String,
    ): Call<MarsPhotos>

}