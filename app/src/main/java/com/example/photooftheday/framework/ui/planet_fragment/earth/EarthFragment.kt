package com.example.photooftheday.framework.ui.planet_fragment.earth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.photooftheday.BuildConfig
import com.example.photooftheday.R
import com.example.photooftheday.databinding.FragmentEarthBinding
import com.example.photooftheday.model.consts.PictureOfTheDayData
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*


class EarthFragment : Fragment() {
    private lateinit var binding: FragmentEarthBinding
    private val viewModel: EarthViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEarthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.liveDataToObserve.observe(viewLifecycleOwner, { renderData(it) })
        viewModel.getData()
    }

    private fun renderData(data: PictureOfTheDayData) {
        when (data) {
            is PictureOfTheDayData.SuccessEarth -> {
                val serverResponseData = data.serverResponseData
                val image = serverResponseData?.image
                if (!image.isNullOrBlank()) {

                    val time = SimpleDateFormat(
                        "yyyy-MM-dd",
                        Locale.getDefault()
                    ).parse(serverResponseData.date)
                    val dateFormat =
                        SimpleDateFormat(FORMAT_DATE, Locale.getDefault())
                            .format(time ?: Calendar.getInstance().time)
                    val url =
                        "$BASE_IMAGE_DIRECTORY$dateFormat/png/$image.png?api_key=${BuildConfig.NASA_API_KEY}"
                    Picasso
                        .get()
                        .load(url)
                        .placeholder(R.drawable.ic_no_photo_vector)
                        .fit()
                        .into(binding.containerImage, object : com.squareup.picasso.Callback {
                            override fun onSuccess() {
                                //set animations here
                            }

                            override fun onError(e: java.lang.Exception?) {
                                //do smth when there is picture loading error
                                e?.printStackTrace()
                            }
                        })
                }
            }
            is PictureOfTheDayData.Loading -> {
                //Отобразите загрузку
                //showLoading()
            }
            is PictureOfTheDayData.Error -> {
            }
        }
    }

    companion object {
        private const val BASE_IMAGE_DIRECTORY = "https://api.nasa.gov/EPIC/archive/natural/"
        private const val FORMAT_DATE = "yyyy/MM/dd"
        fun newInstance() = EarthFragment()
    }
}