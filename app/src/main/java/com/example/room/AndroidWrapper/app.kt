package com.example.room.AndroidWrapper

import android.app.Application
import org.koin.core.context.startKoin

class app : Application() {

    override fun onCreate() {
        super.onCreate()

//        simple koin
        startKoin {
            applicationContext
            modules(

            )
        }

    }

}