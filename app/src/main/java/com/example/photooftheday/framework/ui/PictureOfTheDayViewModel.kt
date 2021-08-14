package com.example.photooftheday.framework.ui


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.photooftheday.model.consts.PictureOfTheDayData
import com.example.photooftheday.model.consts.Types
import com.example.photooftheday.model.data.PODServerResponseData
import com.example.photooftheday.model.rest.PodApiRepositoryImpl
import com.example.photooftheday.model.rest.utils.nextNegativeInt
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

private const val MAX_VAlUE_DAYS = 1000
private const val FORMAT_DATE = "yyyy-MM-dd"

class PictureOfTheDayViewModel(
    val liveDataToObserve: MutableLiveData<PictureOfTheDayData> = MutableLiveData()
) : ViewModel() {

    fun getData(type: Int = 0) {
        val time = Calendar.getInstance()

        when (type) {

            Types.MINUS.value -> time.add(Calendar.DATE, -1)
            Types.RANDOM.value -> time.add(
                Calendar.DATE,
                Random().nextNegativeInt(MAX_VAlUE_DAYS)
            )
        }
        val dateFormat = SimpleDateFormat(FORMAT_DATE, Locale.getDefault()).format(time.time)

        PodApiRepositoryImpl.getPictureOfTheDay(dateFormat, callBack)
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