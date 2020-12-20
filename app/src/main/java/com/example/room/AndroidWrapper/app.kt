package com.example.room.AndroidWrapper

import android.app.Application
import com.example.room.di.*
import org.koin.core.context.startKoin

class app : Application() {

    override fun onCreate() {
        super.onCreate()

//        simple koin
        startKoin {
            applicationContext
            modules(
                appModule,
                fragmentModule,
                activityModule,
                modelModule,
                presenterModule,
                apiModule,
                module
            )
        }

    }

}