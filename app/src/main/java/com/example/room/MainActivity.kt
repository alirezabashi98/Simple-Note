package com.example.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.room.adapter.ListNotesAdapter
import com.example.room.database.MyDatabase
import com.example.room.model.ListNotesModel
import com.example.room.model.NoteModels

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

            recyclerView.adapter = ListNotesAdapter(noteModelsToListNotes(dao?.getAllNote()))
        }.start()

    }

    private fun CastView(){

        recyclerView = findViewById(R.id.rcy_mainActivity_listNote)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

    }

    private fun noteModelsToListNotes(data:List<NoteModels>?):List<ListNotesModel>{
        val datas = arrayListOf<ListNotesModel>()

        data?.forEach {
            datas.add(
                ListNotesModel(it.id!!,it.title,it.note)
            )
        }

        return datas
    }
}