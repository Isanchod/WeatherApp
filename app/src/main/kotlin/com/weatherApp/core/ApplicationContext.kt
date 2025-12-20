package com.weatherApp.core

import android.app.Application
import com.weatherApp.core.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ApplicationContext : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ApplicationContext)
            modules(appModule)
        }
    }
}
