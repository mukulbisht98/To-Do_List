package com.xxmukulxx.to_dolist

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    companion object AppContext {
        lateinit var appContext: Application
    }

    init {
        appContext = this
    }
}