package com.example.photooftheday

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.photooftheday.databinding.ActivityMainBinding
import com.example.photooftheday.framework.ui.PictureOfTheDayFragment
import com.example.photooftheday.model.rest.utils.showFragment

class MainActivity : AppCompatActivity() {
    private lateinit var bind: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setTheme(getPrefTheme())
        setContentView(bind.root)
        if (savedInstanceState == null) {
            PictureOfTheDayFragment.newInstance().showFragment(this)
        }
    }

    fun savePrefTheme(theme : Int){
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        sharedPref?.edit()?.apply {
            bind.apply {
                putInt(STYLE_TAG, theme)
                apply()
            }
        }
        recreate()
    }

    private fun getPrefTheme() : Int{
        var theme = 0
        getPreferences(Context.MODE_PRIVATE)?.let {
            bind.apply {
                it.getInt(STYLE_TAG, NORMAL_THEME).also { theme = it }
            }
        }
        return theme
    }

    companion object {
        const val STYLE_TAG = "STYLE"
        const val NORMAL_THEME = R.style.MyNormalTheme
        const val DARK_THEME = R.style.MyDarkensTheme
        const val HUDI_THEME = R.style.MyHudiColorTheme
    }
}