package com.example.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.room.adapter.ListNotesAdapter
import com.example.room.database.MyDatabase
import com.example.room.model.NoteModels
import com.example.room.utility.ConvertNoteModelToListNotes

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CastView()

        val db = MyDatabase.getMyDatabase(this)
        val dao = db?.noteDAO()

        val note3 = NoteModels(title = "demo",note = "i'm programming android")

        Thread{
            dao?.insert(note3)

            dao?.getNoteById(1)?.forEach { Log.i("ROOM_DB","id : ${it.id} ||| title : ${it.title} ||| note : ${it.note}") }

            recyclerView.adapter = ListNotesAdapter(ConvertNoteModelToListNotes().convertNoteToListNotes(dao?.getAllNote()))
        }.start()

    }

    private fun CastView(){

        recyclerView = findViewById(R.id.rcy_mainActivity_listNote)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

    }
}