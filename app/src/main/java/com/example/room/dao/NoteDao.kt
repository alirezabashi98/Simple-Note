package com.example.room.dao

import androidx.room.*
import com.example.room.model.NoteModels

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(Note: NoteModels)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserts(Notes: List<NoteModels>)

    @Query("SELECT * FROM Note")
    fun getAllNote(): List<NoteModels>

    @Query("SELECT * FROM Note WHERE title = :title")
    fun getNoteByTitle(title: String): List<NoteModels>

    @Query("SELECT * FROM Note WHERE id = :id")
    fun getNoteById(id: Int): List<NoteModels>

    @Delete
    fun DeleteNote(note:NoteModels)

    @Update
    fun updateNote(note:NoteModels)

}