package com.example.room.activity

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.room.R
import com.example.room.dao.NoteDao
import com.example.room.database.MyDatabase
import com.example.room.model.NoteModels
import org.jetbrains.anko.toast

class AddNoteActivity : AppCompatActivity() {

    lateinit var db : MyDatabase
    lateinit var dao : NoteDao

    lateinit var titleNote : EditText
    lateinit var textNote : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        title = "Add Note"

        Cast()

        db = MyDatabase.getMyDatabase(this)!!
        dao = db.noteDAO()

    }

    fun Cast(){

        titleNote = findViewById(R.id.edt_addNoteActivity_title)
        textNote = findViewById(R.id.edt_addNoteActivity_note)

    }

    override fun onBackPressed() {

        if (!titleNote.text.toString().equals("") || !textNote.text.toString().equals("")){

            Thread{

                dao.insert(NoteModels(title = titleNote.text.toString(),note = textNote.text.toString()))

            }.start()

        }else toast("note null")

        super.onBackPressed()
    }
}