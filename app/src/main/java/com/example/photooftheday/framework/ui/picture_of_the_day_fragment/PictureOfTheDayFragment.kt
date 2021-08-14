package com.example.photooftheday.framework.ui.picture_of_the_day_fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.photooftheday.R
import com.example.photooftheday.databinding.FragmentPictureOfTheDayBinding
import com.example.photooftheday.model.consts.PictureOfTheDayData
import com.example.photooftheday.model.consts.Types
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.bottom_sheet_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PictureOfTheDayFragment : Fragment() {
    private lateinit var binding: FragmentPictureOfTheDayBinding
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    //Ленивая инициализация модели
    private val viewModel: PictureOfTheDayViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPictureOfTheDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            inputLayout.setEndIconOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW).apply {
                    data =
                        Uri.parse("$BASE_WIKI_URL${binding.inputEditText.text.toString()}")
                })
            }

            chipGroup.setOnCheckedChangeListener { _, checkedId ->
                var type = 0
                when (checkedId) {
                    yeastradayChip.id -> type = Types.MINUS.value
                    randomChip.id -> type = Types.RANDOM.value
                }
                viewModel.getData(type)
            }
        }

        viewModel.liveDataToObserve.observe(
            viewLifecycleOwner,
            { renderData(it) })
        viewModel.getData()
        setBottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))
    }

    private fun renderData(data: PictureOfTheDayData) {
        when (data) {
            is PictureOfTheDayData.Success -> {
                val serverResponseData = data.serverResponseData
                val url = serverResponseData.url
                if (url.isNullOrEmpty()) {
                    //Отобразите ошибку
                    //showError("Сообщение, что ссылка пустая")
                } else {
                    //Отобразите фото
                    //showSuccess()
                    //Coil в работе: достаточно вызвать у нашего ImageView
                    //нужную extension-функцию и передать ссылку и заглушки для placeholder
                    Picasso
                        .get()
                        .load(serverResponseData.hdurl)
                        .placeholder(R.drawable.ic_no_photo_vector)
                        .fit()
                        .into(binding.imageView, object : com.squareup.picasso.Callback {
                            override fun onSuccess() {
                                //set animations here
                            }

                            override fun onError(e: java.lang.Exception?) {
                                //do smth when there is picture loading error

                            }
                        })

                    bottom_sheet_description_header.text = serverResponseData.title
                    bottom_sheet_description.text = serverResponseData.explanation

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

    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    companion object {
        fun newInstance() = PictureOfTheDayFragment()
        private const val BASE_WIKI_URL = "https://en.wikipedia.org/wiki/"
    }
}