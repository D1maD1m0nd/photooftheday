package com.example.photooftheday.framework.ui.planet_fragment.mars

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.photooftheday.model.consts.PictureOfTheDayData
import com.example.photooftheday.model.data.mars_data.MarsPhotos
import com.example.photooftheday.model.rest.NasaApiRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

private const val FORMAT_DATE = "yyyy-MM-dd"
class MarsViewModel(val liveDataToObserve: MutableLiveData<PictureOfTheDayData> = MutableLiveData()) :
    ViewModel() {

    fun getData() {
        val time = Calendar.getInstance()
        time.add(Calendar.DATE, -1)
        val dateFormat = SimpleDateFormat(FORMAT_DATE, Locale.getDefault()).format(time.time)
        NasaApiRepositoryImpl.getMarsImages(
            dateFormat,
            "FHAZ",
            callBack
        )
    }

    private val callBack = object :
        Callback<MarsPhotos> {
        override fun onResponse(
            call: Call<MarsPhotos>,
            response: Response<MarsPhotos>
        ) {
            val responseData = response.body()
            if (response.isSuccessful && responseData != null && responseData.photos.isNotEmpty()) {
                liveDataToObserve.value =
                    PictureOfTheDayData.SuccessMars(responseData.photos.first())
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

        override fun onFailure(call: Call<MarsPhotos>, t: Throwable) {
            liveDataToObserve.value = PictureOfTheDayData.Error(t)
        }
    }
}