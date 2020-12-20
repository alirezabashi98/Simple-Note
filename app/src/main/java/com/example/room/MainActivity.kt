package com.example.room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.room.adapter.ListNotesAdapter
import com.example.room.database.MyDatabase
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

        Thread {

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