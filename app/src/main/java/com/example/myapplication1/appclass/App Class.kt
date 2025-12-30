package com.example.myapplication1.appclass

import android.app.Application
import com.example.myapplication1.repository.RepositoryProvider

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        RepositoryProvider.init(this)
    }
}