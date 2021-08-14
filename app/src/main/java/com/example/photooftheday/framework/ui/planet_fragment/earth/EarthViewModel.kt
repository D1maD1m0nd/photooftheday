package com.example.photooftheday.framework.ui.planet_fragment.earth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.photooftheday.model.consts.PictureOfTheDayData
import com.example.photooftheday.model.data.earth_data.Earth
import com.example.photooftheday.model.rest.NasaApiRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

private const val FORMAT_DATE = "yyyy-MM-dd"
class EarthViewModel(val liveDataToObserve: MutableLiveData<PictureOfTheDayData> = MutableLiveData()) :
    ViewModel() {

    fun getData() {
        val time = Calendar.getInstance()
        val dateFormat = SimpleDateFormat(FORMAT_DATE, Locale.getDefault()).format(time.time)
        NasaApiRepositoryImpl.getEarthImages(dateFormat, callBack)
    }

    private val callBack = object :
        Callback<List<Earth>> {
        override fun onResponse(
            call: Call<List<Earth>>,
            response: Response<List<Earth>>
        ) {
            val responseData = response.body()
            if (response.isSuccessful && responseData != null) {
                liveDataToObserve.value =
                    PictureOfTheDayData.SuccessEarth(responseData.first())
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

        override fun onFailure(call: Call<List<Earth>>, t: Throwable) {
            liveDataToObserve.value = PictureOfTheDayData.Error(t)
        }

    }
}