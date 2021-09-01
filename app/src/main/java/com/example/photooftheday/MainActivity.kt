package com.example.photooftheday

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.photooftheday.databinding.ActivityMainBinding
import com.example.photooftheday.extansions.showFragment
import com.example.photooftheday.framework.ui.info_fragment.InfoFragment
import com.example.photooftheday.framework.ui.picture_of_the_day_fragment.PictureOfTheDayFragment
import com.example.photooftheday.framework.ui.planet_fragment.PlanetsInfoFragment
import com.example.photooftheday.framework.ui.settings_fragment.SettingsFragment


class MainActivity : AppCompatActivity() {
    private lateinit var bind: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setTheme(getPrefTheme())
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)



        initBottomNavigationMenu()

    }
    private fun initBottomNavigationMenu() = with(bind) {


    navView.setOnItemSelectedListener { item ->

        when (item.itemId) {
            R.id.home -> PictureOfTheDayFragment.newInstance().showFragment(this@MainActivity)
            R.id.app_bar_fav -> PlanetsInfoFragment.newInstance()
                .showFragment(this@MainActivity)
            R.id.app_bar_choice_theme -> SettingsFragment.newInstance()
                .showFragment(this@MainActivity)
            R.id.app_bar_settings -> InfoFragment.newInstance()
                .showFragment(this@MainActivity)
            else -> PictureOfTheDayFragment.newInstance().showFragment(this@MainActivity)
        }


        setStyleBottomNavigationMenu()
        navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> PictureOfTheDayFragment.newInstance().showFragment(this@MainActivity)
                R.id.app_bar_fav -> PlanetsInfoFragment.newInstance()
                    .showFragment(this@MainActivity)
                R.id.app_bar_choice_theme -> SettingsFragment.newInstance()
                    .showFragment(this@MainActivity)
                else -> PictureOfTheDayFragment.newInstance().showFragment(this@MainActivity)
            }
            return@setOnItemSelectedListener true
        }
        navView.selectedItemId = R.id.home
    }

    private fun setStyleBottomNavigationMenu() = with(bind) {
        val theme = getPrefTheme()
        val color = when (theme) {
            DARK_THEME -> R.color.darkens
            NORMAL_THEME -> R.color.color_gray
            else -> R.color.colorAccent
        }
        val checkedColor = when (theme) {
            DARK_THEME -> R.color.white
            NORMAL_THEME -> R.color.white
            else -> R.color.white

        if (savedInstanceState == null) {
            initBottomNavigationMenu()

        }
        val uncheckedColor = when (theme) {
            DARK_THEME -> R.color.none_selected_items
            NORMAL_THEME -> R.color.color_gray
            else -> R.color.color_gray
        }
        val states = arrayOf(intArrayOf(android.R.attr.state_checked), intArrayOf())
        val colors = intArrayOf(
            ContextCompat.getColor(this@MainActivity, checkedColor),
            ContextCompat.getColor(this@MainActivity, uncheckedColor)
        )

        val myList = ColorStateList(states, colors)
        navView.setBackgroundColor(ContextCompat.getColor(this@MainActivity, color))
        navView.itemIconTintList = myList
        navView.itemTextColor = myList

    }
    private fun initBottomNavigationMenu() = with(bind) {
        navView.setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.home -> PictureOfTheDayFragment.newInstance().showFragment(this@MainActivity)
                R.id.app_bar_fav -> PlanetsInfoFragment.newInstance()
                    .showFragment(this@MainActivity)
                R.id.app_bar_choice_theme -> SettingsFragment.newInstance()
                    .showFragment(this@MainActivity)
                else -> PictureOfTheDayFragment.newInstance().showFragment(this@MainActivity)
            }
            return@setOnItemSelectedListener true
        }
        navView.selectedItemId = R.id.home
    }

    fun savePrefTheme(theme: Int) {
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