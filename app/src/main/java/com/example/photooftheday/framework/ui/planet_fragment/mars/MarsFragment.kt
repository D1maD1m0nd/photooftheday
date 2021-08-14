package com.example.photooftheday.framework.ui.planet_fragment.mars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.photooftheday.R
import com.example.photooftheday.databinding.FragmentMarsBinding
import com.example.photooftheday.model.consts.PictureOfTheDayData
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel


class MarsFragment : Fragment() {
    private lateinit var binding: FragmentMarsBinding
    private val viewModel: MarsViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMarsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.liveDataToObserve.observe(viewLifecycleOwner, { renderData(it) })
        viewModel.getData()
    }

    private fun renderData(data: PictureOfTheDayData) {
        when (data) {
            is PictureOfTheDayData.SuccessMars -> {
                val serverResponseData = data.serverResponseData
                val image = serverResponseData?.imageSrc
                Picasso
                    .get()
                    .load(image)
                    .placeholder(R.drawable.ic_no_photo_vector)
                    .fit()
                    .into(binding.containerImage, object : com.squareup.picasso.Callback {
                        override fun onSuccess() {
                            //set animations here
                        }

                        override fun onError(e: java.lang.Exception?) {
                            //do smth when there is picture loading error

                        }
                    })
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

        fun newInstance() = MarsFragment()
    }
}