package com.example.photooftheday.framework.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.photooftheday.model.consts.PictureOfTheDayData
import com.example.photooftheday.model.data.PODServerResponseData
import com.example.photooftheday.model.rest.PodApiRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PictureOfTheDayViewModel(
    private val liveDataToObserve: MutableLiveData<PictureOfTheDayData> = MutableLiveData(),

    ) : ViewModel() {

    fun getData(): LiveData<PictureOfTheDayData> {
        PodApiRepositoryImpl.getPictureOfTheDay(callBack)
        return liveDataToObserve
    }

    private val callBack = object :
        Callback<PODServerResponseData> {
        override fun onResponse(
            call: Call<PODServerResponseData>,
            response: Response<PODServerResponseData>
        ) {
            val responseData = response.body()
            if (response.isSuccessful && responseData != null) {
                liveDataToObserve.value =
                    PictureOfTheDayData.Success(responseData)
            } else {
                val message = response.message()
                if (message.isNullOrEmpty()) {
                    liveDataToObserve.value =
                        PictureOfTheDayData.Error(Throwable("Unidentified error"))
                } else {
                    liveDataToObserve.value =
                        PictureOfTheDayData.Error(Throwable(message))
                }
            }
        }

        override fun onFailure(call: Call<PODServerResponseData>, t: Throwable) {
            liveDataToObserve.value = PictureOfTheDayData.Error(t)
        }

    }
}