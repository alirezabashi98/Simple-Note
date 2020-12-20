package com.example.room.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.room.R
import com.example.room.adapter.ListNotesAdapter
import com.example.room.database.MyDatabase
import com.example.room.utility.ConvertNoteModelToListNotes
import org.jetbrains.anko.startActivity
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    val convertNotesModelToListNotes: ConvertNoteModelToListNotes by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CastView()
        title = "Notes"

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val menuInflater = menuInflater.inflate(R.menu.add_note, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.item_menuAddNote -> startActivity<AddNoteActivity>()
            else -> {
            }

        }

        return true
    }
}