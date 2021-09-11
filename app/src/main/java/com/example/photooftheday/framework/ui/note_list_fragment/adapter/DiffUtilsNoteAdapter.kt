package com.example.photooftheday.framework.ui.note_list_fragment.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.photooftheday.model.data.note_data.Note

class DiffUtilsNoteAdapter(
    private val oldList: ArrayList<Note>,
    private val newList: ArrayList<Note>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return false
    }
}