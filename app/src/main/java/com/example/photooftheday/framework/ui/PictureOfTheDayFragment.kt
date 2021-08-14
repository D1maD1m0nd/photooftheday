package com.example.photooftheday.framework.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.photooftheday.MainActivity
import com.example.photooftheday.R
import com.example.photooftheday.databinding.FragmentPictureOfTheDayBinding
import com.example.photooftheday.model.consts.PictureOfTheDayData
import com.example.photooftheday.model.consts.Types
import com.example.photooftheday.model.rest.utils.showFragment
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.bottom_sheet_layout.*
import kotlinx.android.synthetic.main.fragment_picture_of_the_day.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PictureOfTheDayFragment : Fragment() {
    private lateinit var binding: FragmentPictureOfTheDayBinding
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    //Ленивая инициализация модели
    private val viewModel: PictureOfTheDayViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPictureOfTheDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            inputLayout.setEndIconOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW).apply {
                    data =
                        Uri.parse("$BASE_WIKI_URL${binding.inputEditText.text.toString()}")
                })
            }

            chipGroup.setOnCheckedChangeListener { _, checkedId ->
                var type = 0
                when (checkedId) {
                    yeastradayChip.id -> type = Types.MINUS.value
                    randomChip.id -> type = Types.RANDOM.value
                }
                viewModel.getData(type)
            }
        }

        viewModel.liveDataToObserve.observe(
            viewLifecycleOwner,
            { renderData(it) })
        viewModel.getData()
        setBottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))
        setBottomAppBar(view)
    }

    private fun renderData(data: PictureOfTheDayData) {
        when (data) {
            is PictureOfTheDayData.Success -> {
                val serverResponseData = data.serverResponseData
                val url = serverResponseData.url
                if (url.isNullOrEmpty()) {
                    //Отобразите ошибку
                    //showError("Сообщение, что ссылка пустая")
                } else {
                    //Отобразите фото
                    //showSuccess()
                    //Coil в работе: достаточно вызвать у нашего ImageView
                    //нужную extension-функцию и передать ссылку и заглушки для placeholder
                    Picasso
                        .get()
                        .load(serverResponseData.hdurl)
                        .placeholder(R.drawable.ic_no_photo_vector)
                        .fit()
                        .into(binding.imageView, object : com.squareup.picasso.Callback {
                            override fun onSuccess() {
                                //set animations here
                            }

                            override fun onError(e: java.lang.Exception?) {
                                //do smth when there is picture loading error

                            }
                        })

                    bottom_sheet_description_header.text = serverResponseData.title
                    bottom_sheet_description.text = serverResponseData.explanation

                }
            }
            is PictureOfTheDayData.Loading -> {
                //Отобразите загрузку
                //showLoading()
            }
            is PictureOfTheDayData.Error -> {
            }
        }
    }

    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.bottom_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_fav -> Toast.makeText(context, "Favourite", Toast.LENGTH_SHORT).show()
            R.id.app_bar_choice_theme -> showFragment(SettingsFragment.newInstance())
            R.id.app_bar_settings -> Toast.makeText(context, "Settings", Toast.LENGTH_SHORT).show()


            android.R.id.home -> {
                activity?.let {
                    BottomNavigationDrawerFragment().show(it.supportFragmentManager, "tag")
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setBottomAppBar(view: View) {
        val context = activity as MainActivity
        context.setSupportActionBar(view.findViewById(R.id.bottom_app_bar))
        setHasOptionsMenu(true)
        fab.setOnClickListener {
            if (isMain) {
                isMain = false
                bottom_app_bar.navigationIcon = null
                bottom_app_bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                fab.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_back_fab))
                bottom_app_bar.replaceMenu(R.menu.menu_bottom_bar_other_screen)
            } else {
                isMain = true
                bottom_app_bar.navigationIcon =
                    ContextCompat.getDrawable(context, R.drawable.ic_hamburger_menu_bottom_bar)
                bottom_app_bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                fab.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_plus_fab))
                bottom_app_bar.replaceMenu(R.menu.bottom_menu)
            }
        }
    }

    companion object {
        fun newInstance() = PictureOfTheDayFragment()
        private var isMain = true
        private const val BASE_WIKI_URL = "https://en.wikipedia.org/wiki/"
    }
}