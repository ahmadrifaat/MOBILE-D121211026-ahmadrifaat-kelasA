package com.D121211026.alquran

import android.app.Application
import com.D121211026.alquran.data.AppContainer
import com.D121211026.alquran.data.DefaultAppContainer

class MyApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}