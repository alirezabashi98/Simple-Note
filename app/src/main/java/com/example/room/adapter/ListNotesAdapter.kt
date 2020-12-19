package com.example.room.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.room.R
import com.example.room.model.ListNotesModel

class ListNotesAdapter(private val data: List<ListNotesModel>) :
    RecyclerView.Adapter<ListNotesAdapter.ViewHolderListNotes>() {

    inner class ViewHolderListNotes(view: View) : RecyclerView.ViewHolder(view) {

        val title: TextView = view.findViewById(R.id.tv_itemDemoNotes_title)
        val notes: TextView = view.findViewById(R.id.tv_itemDemoNotes_notes)

        fun setData(NoteData: ListNotesModel) {
            title.text = NoteData.title
            notes.text = NoteData.note
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderListNotes =
        ViewHolderListNotes(
            LayoutInflater.from(parent?.context).inflate(R.layout.item_demo_notes, null)
        )

    override fun onBindViewHolder(holder: ViewHolderListNotes, position: Int) =
        holder.setData(data[position])

    override fun getItemCount(): Int = data.size

}