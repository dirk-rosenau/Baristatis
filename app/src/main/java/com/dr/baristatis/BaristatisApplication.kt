package com.dr.baristatis

import android.app.Application
import com.dr.baristatis.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaristatisApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // start Koin!
        startKoin {
            // declare used Android context
            androidContext(this@BaristatisApplication)
            // declare modules
            modules(mainModule)
        }
    }
}