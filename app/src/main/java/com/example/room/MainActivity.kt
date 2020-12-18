package com.example.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.room.database.MyDatabase
import com.example.room.model.NoteModels

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = MyDatabase.getMyDatabase(this)
        val dao = db?.noteDAO()

        val note3 = NoteModels(id = 3,title = "developer",note = "i;m programming android")

        Thread{
            //dao?.DeleteNote(note3)

            dao?.getAllNote()?.forEach { Log.i("ROOM_DB","id : ${it.id} ||| title : ${it.title} ||| note : ${it.note}") }

        }.start()

    }
}