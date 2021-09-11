package com.example.photooftheday.framework.ui.note_list_fragment.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.photooftheday.model.data.note_data.Note

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(dataItem: Note)
}