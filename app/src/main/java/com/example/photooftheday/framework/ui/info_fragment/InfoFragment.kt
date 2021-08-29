package com.example.photooftheday.framework.ui.info_fragment

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
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
        setStyleText()
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun setStyleText() {
        val text =
            "Список возможностей <ul><li>Фото дня</li><li>Фото марса</li><li>Фото Земли</li></ul>"
        binding.list.text = Html.fromHtml(text, 1)

        val aboutAppString = resources.getString(R.string.about_app)
        val spannable = SpannableStringBuilder(aboutAppString)
        spannable.setSpan(
            ForegroundColorSpan(Color.GREEN),
            0, 6,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        spannable.setSpan(
            ForegroundColorSpan(Color.BLUE),
            6, aboutAppString.length,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        spannable.setSpan(
            BackgroundColorSpan(Color.YELLOW),
            0, aboutAppString.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.title.text = spannable
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