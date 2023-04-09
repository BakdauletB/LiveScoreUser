package com.example.livescoreuser

import android.app.Application
import com.example.livescoresdu.di.configureKoin

class App : Application(){

    override fun onCreate() {
        super.onCreate()
        configureKoin(this)

    }
}