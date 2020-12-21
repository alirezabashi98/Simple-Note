package com.example.room.activity

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.room.R
import com.example.room.dao.NoteDao
import com.example.room.database.MyDatabase
import com.example.room.model.NoteModels
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class AddNoteActivity : AppCompatActivity() {

    lateinit var db: MyDatabase
    lateinit var dao: NoteDao

    lateinit var titleNote: EditText
    lateinit var textNote: EditText

    var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        title = "Add Note"

        Cast()

        db = MyDatabase.getMyDatabase(this)!!
        dao = db.noteDAO()

        id = intent.getIntExtra("idNote", -1)

        titleNote.setText(intent.getStringExtra("titleNote"))
        textNote.setText(intent.getStringExtra("textNote"))
    }

    fun Cast() {

        titleNote = findViewById(R.id.edt_addNoteActivity_title)
        textNote = findViewById(R.id.edt_addNoteActivity_note)

    }

    override fun onBackPressed() {

        if (id != -1 && id != null) {
            Thread {

                dao.updateNote(
                    NoteModels(
                        id = id,
                        title = titleNote.text.toString(),
                        note = textNote.text.toString()
                    )
                )

            }.start()

        } else if (!titleNote.text.toString().equals("") || !textNote.text.toString().equals("")) {

            Thread {

                dao.insert(
                    NoteModels(
                        title = titleNote.text.toString(),
                        note = textNote.text.toString()
                    )
                )

            }.start()

        } else toast("Nothing saved")

        startActivity<MainActivity>()
        finishAffinity()
    }
}