package com.hamza.itiproject

import android.app.Application
import com.hamza.itiproject.utils.MySharedPreferences

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        MySharedPreferences.init(this)
    }
}