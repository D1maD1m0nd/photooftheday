package com.example.photooftheday.framework.ui.note_list_fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.photooftheday.databinding.FragmentNoteListBinding
import com.example.photooftheday.framework.ui.note_list_fragment.adapter.DiffUtilsNoteAdapter
import com.example.photooftheday.framework.ui.note_list_fragment.adapter.NoteAdapter
import com.example.photooftheday.model.data.note_data.NoteGeneration


class NoteListFragment : Fragment() {
    private val mAdapter = NoteAdapter()
    lateinit var bind: FragmentNoteListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentNoteListBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.rcViewMain.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        mAdapter.setData(NoteGeneration.generate())

        Handler().postDelayed({
            Toast.makeText(requireContext(), "DATA IS GET", Toast.LENGTH_SHORT).show()
            val notes = NoteGeneration.generate()
            val utils = DiffUtilsNoteAdapter(mAdapter.list, notes)
            val diffResult = DiffUtil.calculateDiff(utils)
            mAdapter.setData(notes)
            diffResult.dispatchUpdatesTo(mAdapter)
        }, 9000)
    }

    companion object {

        fun newInstance() = NoteListFragment()
    }
}