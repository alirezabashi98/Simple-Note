package com.example.room.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.room.R

class AddNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        title = "Add Note"

    }
}