package com.talischeung.keysoc_assessment

import android.app.Application
import android.content.SharedPreferences

class DemoApplication: Application() {
    companion object {
        lateinit var sharedPreferences: SharedPreferences
    }

    override fun onCreate() {
        super.onCreate()
        sharedPreferences = getSharedPreferences("keysoc.sharedpreferences", MODE_PRIVATE)
    }
}