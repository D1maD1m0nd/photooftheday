package com.example.photooftheday.framework.ui.settings_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.photooftheday.MainActivity
import com.example.photooftheday.R
import kotlinx.android.synthetic.main.fragment_settings.*


class SettingsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context = requireActivity() as MainActivity
        buttonDark.setOnClickListener {
            context.savePrefTheme(MainActivity.DARK_THEME)
        }

        buttonHudi.setOnClickListener {
            context.savePrefTheme(MainActivity.HUDI_THEME)
        }

        buttonNormal.setOnClickListener {
            context.savePrefTheme(MainActivity.NORMAL_THEME)
        }
    }

    companion object {
        fun newInstance() = SettingsFragment()
    }
}