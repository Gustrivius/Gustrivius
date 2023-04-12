package com.example.gustrivius

import android.app.Application

class questionIntentApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        questionRepository.initialize(this)
    }
}
