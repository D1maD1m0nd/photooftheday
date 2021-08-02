package com.example.photooftheday.di


import com.example.photooftheday.framework.ui.PictureOfTheDayViewModel
import com.example.photooftheday.model.rest.PodApiRepositoryImpl
import get
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    //View models
    viewModel { PictureOfTheDayViewModel(get()) }
}
