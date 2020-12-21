package com.example.room.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.airbnb.lottie.LottieAnimationView
import com.example.room.R
import com.example.room.adapter.ListNotesAdapter
import com.example.room.dao.NoteDao
import com.example.room.database.MyDatabase
import com.example.room.utility.ConvertNoteModelToListNotes
import org.jetbrains.anko.startActivity
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var lottieAnim: LottieAnimationView
    val convertNotesModelToListNotes: ConvertNoteModelToListNotes by inject()

    lateinit var db: MyDatabase
    lateinit var dao: NoteDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Notes"

        CastView()

        db = MyDatabase.getMyDatabase(this)!!
        dao = db.noteDAO()

        Thread {

            if (dao.getAllNote().isEmpty()) {
                recyclerView.visibility = View.GONE
                lottieAnim.visibility = View.VISIBLE
            } else {

                recyclerView.visibility = View.VISIBLE
                lottieAnim.visibility = View.GONE

                recyclerView.adapter =
                    ListNotesAdapter(convertNotesModelToListNotes.convertNoteToListNotes(dao.getAllNote()))

            }
        }.start()

    }

    private fun CastView() {

        recyclerView = findViewById(R.id.rcy_mainActivity_listNote)
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        lottieAnim = findViewById(R.id.aView_mainActivity_Empty)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val menuInflater = menuInflater.inflate(R.menu.add_note, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.item_menuAddNote -> {
                startActivity<AddNoteActivity>()
                finish()
            }
            else -> {
            }

        }

        return true
    }
}