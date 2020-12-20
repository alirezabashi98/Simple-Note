package com.example.room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.room.adapter.ListNotesAdapter
import com.example.room.database.MyDatabase
import com.example.room.model.NoteModels
import com.example.room.utility.ConvertNoteModelToListNotes
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    val convertNotesModelToListNotes: ConvertNoteModelToListNotes by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CastView()

        val db = MyDatabase.getMyDatabase(this)
        val dao = db?.noteDAO()

//        val dataNotes = listOf(
//            NoteModels(title = "telegram",note = "t.me/yardeveloper"),
//            NoteModels(title = "instagram",note = "instagram.com/yardeveloper"),
//            NoteModels(title = "developer",note = "developer android"),
//            NoteModels(title = "MyName",note = "i'm alirezabashi98"),
//            NoteModels(title = "MyNames",note = "i'm alirezabashi98 i'm alirezabashi98i'm alirezabashi98i'm alirezabashi98i'm alirezabashi98")
//        )

        Thread {

//            dao?.inserts(dataNotes)

            recyclerView.adapter =
                ListNotesAdapter(convertNotesModelToListNotes.convertNoteToListNotes(dao?.getAllNote()))

        }.start()

    }

    private fun CastView() {

        recyclerView = findViewById(R.id.rcy_mainActivity_listNote)
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

    }
}