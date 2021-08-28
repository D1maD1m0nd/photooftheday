package com.example.photooftheday.framework.ui.info_fragment

import android.os.Build
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.BulletSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.photooftheday.R
import com.example.photooftheday.databinding.FragmentInfoBinding


class InfoFragment : Fragment() {
    private lateinit var binding: FragmentInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val string = SpannableString("abc\n123\n")

        val color = ContextCompat.getColor(requireContext(), R.color.colorPrimary)
        string.setSpan(BulletSpan(1, color, 5), 0, string.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        string.setSpan(BulletSpan(1, color, 5), 4, string.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.list.text = string
    }

    companion object {
        fun newInstance(): Fragment {
            val args = Bundle()
            val fragment = InfoFragment()
            fragment.arguments = args
            return fragment
        }
    }
}