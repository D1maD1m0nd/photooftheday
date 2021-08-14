package com.example.photooftheday.di


import com.example.photooftheday.framework.ui.picture_of_the_day_fragment.PictureOfTheDayViewModel
import com.example.photooftheday.framework.ui.planet_fragment.earth.EarthViewModel
import com.example.photooftheday.framework.ui.planet_fragment.mars.MarsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    //View models
    viewModel { PictureOfTheDayViewModel() }
    viewModel { EarthViewModel() }
    viewModel { MarsViewModel() }
}
