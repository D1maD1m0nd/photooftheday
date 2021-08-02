package com.example.photooftheday.model.data

import com.google.gson.annotations.SerializedName

data class PODServerResponseData(
    val copyright: String?,
    val date: String?,
    val explanation: String?,
    @SerializedName("media_type")
    val mediaType: String?,
    val title: String?,
    val url: String?,
    val hdurl: String?
)
