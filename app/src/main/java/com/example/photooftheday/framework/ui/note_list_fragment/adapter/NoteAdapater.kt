package com.example.photooftheday.framework.ui.note_list_fragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.photooftheday.R
import com.example.photooftheday.databinding.ActiveNoteItemBinding
import com.example.photooftheday.databinding.CancelNoteItemBinding
import com.example.photooftheday.databinding.DisabledNoteItemBinding
import com.example.photooftheday.model.consts.DateFormatEnums
import com.example.photooftheday.model.consts.NoteEnum
import com.example.photooftheday.model.data.note_data.Note
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NoteAdapter : RecyclerView.Adapter<BaseViewHolder>() {
    var list = ArrayList<Note>(5000)


    fun setData(data: ArrayList<Note>) {
        list.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            NoteEnum.DISABLED.value -> DisableNoteViewHolder(
                inflater.inflate(
                    R.layout.disabled_note_item,
                    parent,
                    false
                ) as View
            )
            NoteEnum.ACTIVE.value -> ActiveNoteViewHolder(
                inflater.inflate(
                    R.layout.active_note_item,
                    parent,
                    false
                ) as View
            )
            else -> CancelNoteViewHolder(
                inflater.inflate(
                    R.layout.cancel_note_item,
                    parent,
                    false
                ) as View
            )
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (list[position].type) {
            1 -> NoteEnum.DISABLED.value
            2 -> NoteEnum.ACTIVE.value
            else -> NoteEnum.CANCEL.value
        }
    }

    inner class ActiveNoteViewHolder(item: View) : BaseViewHolder(item) {
        private val binding: ActiveNoteItemBinding = ActiveNoteItemBinding.bind(item)
        override fun bind(dataItem: Note) = with(binding) {
            title.text = dataItem.name
            description.text = dataItem.description
            date.text = SimpleDateFormat(
                DateFormatEnums.DOTE_DATE.value,
                Locale.getDefault()
            ).format(Calendar.getInstance().time)
        }
    }

    inner class CancelNoteViewHolder(item: View) : BaseViewHolder(item) {
        private val binding: CancelNoteItemBinding = CancelNoteItemBinding.bind(item)
        override fun bind(dataItem: Note) = with(binding) {
            title.text = dataItem.name
            description.text = dataItem.description
            date.text = SimpleDateFormat(
                DateFormatEnums.DOTE_DATE.value,
                Locale.getDefault()
            ).format(Calendar.getInstance().time)
        }
    }

    inner class DisableNoteViewHolder(item: View) : BaseViewHolder(item) {
        private val binding: DisabledNoteItemBinding = DisabledNoteItemBinding.bind(item)
        override fun bind(dataItem: Note) = with(binding) {
            title.text = dataItem.name
            description.text = dataItem.description
            date.text = SimpleDateFormat(
                DateFormatEnums.DOTE_DATE.value,
                Locale.getDefault()
            ).format(Calendar.getInstance().time)
        }
    }

}

