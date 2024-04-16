package com.example.famsafe

import android.app.Application
import com.google.android.play.integrity.internal.k

class FamSafeApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPreference.init(this)
    }
}