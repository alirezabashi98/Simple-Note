package com.example.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.room.dao.NoteDao
import com.example.room.model.NoteModels

@Database(entities = [NoteModels::class], version = 1)
abstract class MyDatabase : RoomDatabase() {

    abstract fun noteDAO(): NoteDao

    companion object {

        private var INSTANCE: MyDatabase? = null

        fun getMyDatabase(context: Context): MyDatabase? {

            if (INSTANCE == null) {

                INSTANCE =
                    Room.databaseBuilder(
                        context.applicationContext,
                        MyDatabase::class.java,
                        "noteDB"
                    ).build()

            }

            return INSTANCE

        }

        fun destroyDatabase() {
            INSTANCE = null
        }

    }

}