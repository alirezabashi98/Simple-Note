package com.example.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Note")
data class NoteModels(

    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    @ColumnInfo
    val title: String,

    @ColumnInfo
    val note: String
)