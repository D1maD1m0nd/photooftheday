package com.example.photooftheday.model.consts

import com.example.photooftheday.model.data.PODServerResponseData
import com.example.photooftheday.model.data.earth_data.Earth
import com.example.photooftheday.model.data.mars_data.Mars

sealed class PictureOfTheDayData {
    data class Success(val serverResponseData: PODServerResponseData) : PictureOfTheDayData()
    data class SuccessEarth(val serverResponseData: Earth?) : PictureOfTheDayData()
    data class SuccessMars(val serverResponseData: Mars?) : PictureOfTheDayData()
    data class Error(val error: Throwable) : PictureOfTheDayData()
    data class Loading(val progress: Int?) : PictureOfTheDayData()
}
