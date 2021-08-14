package com.example.photooftheday.framework.ui.planet_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.photooftheday.databinding.FragmentPlanetsInfoBinding
import com.example.photooftheday.framework.ui.planet_fragment.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator


class PlanetsInfoFragment : Fragment() {
    private lateinit var binding: FragmentPlanetsInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlanetsInfoBinding.inflate(inflater, container, false)
        return binding.root
        // Inflate the layout for this fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val manager = requireActivity().supportFragmentManager
        binding.apply {
            viewPager.adapter = ViewPagerAdapter(manager, lifecycle)
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                when (position) {
                    0 -> {
                        tab.text = "EARTH"
                    }
                    1 -> {
                        tab.text = "MARS"
                    }
                    2 -> {
                        tab.text = "PLANET"
                    }
                }
            }.attach()
        }

    }

    companion object {

        fun newInstance() = PlanetsInfoFragment()

    }
}