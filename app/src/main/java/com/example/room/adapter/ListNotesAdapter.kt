package com.example.room.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.room.R
import com.example.room.activity.AddNoteActivity
import com.example.room.model.ListNotesModel
import org.jetbrains.anko.startActivity

class ListNotesAdapter(private var data: List<ListNotesModel>) :
    RecyclerView.Adapter<ListNotesAdapter.ViewHolderListNotes>() {

    inner class ViewHolderListNotes(view: View) : RecyclerView.ViewHolder(view) {

        val title: TextView = view.findViewById(R.id.tv_itemDemoNotes_title)
        val notes: TextView = view.findViewById(R.id.tv_itemDemoNotes_notes)
        val views = view
        fun setData(NoteData: ListNotesModel) {
            title.text = NoteData.title
            notes.text = NoteData.note
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderListNotes =
        ViewHolderListNotes(
            LayoutInflater.from(parent.context).inflate(R.layout.item_demo_notes, null)
        )

    override fun onBindViewHolder(holder: ViewHolderListNotes, position: Int) {

        holder.setData(data[position])

        holder.views.setOnClickListener {

            holder.views.context.startActivity<AddNoteActivity>(
                "idNote" to data[position].id,
                "titleNote" to data[position].title,
                "textNote" to data[position].note
            )

        }
    }

    override fun getItemCount(): Int = data.size

    fun onRestart(dataNew: List<ListNotesModel>) {
        data = dataNew
        notifyDataSetChanged()
    }

}