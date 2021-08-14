package com.example.photooftheday.framework.ui.planet_fragment.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.photooftheday.framework.ui.planet_fragment.earth.EarthFragment
import com.example.photooftheday.framework.ui.planet_fragment.mars.MarsFragment
import com.example.photooftheday.framework.ui.planet_fragment.weather.WeatherFragment


class ViewPagerAdapter(private val fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragments = arrayOf(EarthFragment(), MarsFragment(), WeatherFragment())

    companion object {
        private const val EARTH_FRAGMENT = 0
        private const val MARS_FRAGMENT = 1
        private const val WEATHER_FRAGMENT = 2
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> fragments[EARTH_FRAGMENT]
            1 -> fragments[MARS_FRAGMENT]
            2 -> fragments[WEATHER_FRAGMENT]
            else -> fragments[EARTH_FRAGMENT]
        }
    }
}