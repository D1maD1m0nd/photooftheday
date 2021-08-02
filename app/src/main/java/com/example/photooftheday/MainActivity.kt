package com.example.photooftheday

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.photooftheday.framework.ui.PictureOfTheDayFragment
import com.example.photooftheday.model.rest.utils.showFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        savedInstanceState.let {
            PictureOfTheDayFragment.newInstance().showFragment(this)
        }
    }
}