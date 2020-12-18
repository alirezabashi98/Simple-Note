package com.example.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.room.model.NoteModels

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note:NoteModels)

    @Query("SELECT * FROM Note")
    fun getAllNote():List<NoteModels>

}