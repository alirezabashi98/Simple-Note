package com.example.room.di

import com.example.room.database.MyDatabase
import com.example.room.utility.ConvertNoteModelToListNotes
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.KoinContext
import org.koin.dsl.koinApplication
import org.koin.dsl.module

val appModule = module {

    factory { ConvertNoteModelToListNotes() }

}

val fragmentModule = module {}

val activityModule = module {}

val modelModule = module {}

val presenterModule = module {}

val apiModule = module {}

val module = module {}