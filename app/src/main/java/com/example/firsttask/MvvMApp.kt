package com.example.firsttask

import android.app.Application
import android.content.Context

class MvvMApp : Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: MvvMApp? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        // initialize for any
        // Use ApplicationContext.
        // example: SharedPreferences etc...
        val context: Context = MvvMApp.applicationContext()
    }
}