package com.example.room.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.room.R
import com.example.room.activity.AddNoteActivity
import com.example.room.dao.NoteDao
import com.example.room.database.MyDatabase
import com.example.room.model.ListNotesModel
import com.example.room.utility.ConvertNoteModelToListNotes
import org.jetbrains.anko.startActivity
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent.inject

class ListNotesAdapter(private var data: List<ListNotesModel>) :
    RecyclerView.Adapter<ListNotesAdapter.ViewHolderListNotes>() {

    //    todo
    lateinit var db: MyDatabase
    lateinit var dao: NoteDao
    val convertNotesModelToListNotes = ConvertNoteModelToListNotes()

    inner class ViewHolderListNotes(view: View) : RecyclerView.ViewHolder(view) {

        val title: TextView = view.findViewById(R.id.tv_itemDemoNotes_title)
        val notes: TextView = view.findViewById(R.id.tv_itemDemoNotes_notes)
        val views = view
        fun setData(NoteData: ListNotesModel) {
            title.text = NoteData.title
            notes.text = NoteData.note
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderListNotes {

//        todo
        db = MyDatabase.getMyDatabase(parent.context)!!
        dao = db.noteDAO()

        return ViewHolderListNotes(
            LayoutInflater.from(parent.context).inflate(R.layout.item_demo_notes, null)
        )
    }

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

    //    todo
    fun deleteNote(position: Int) {
//        todo
        Thread {

            var noteDelete = dao.getNoteById(data[position].id)
            dao.DeleteNote(noteDelete)
            onRestart(
                convertNotesModelToListNotes.convertNoteToListNotes(dao.getAllNote())
            )

        }.start()

    }

}