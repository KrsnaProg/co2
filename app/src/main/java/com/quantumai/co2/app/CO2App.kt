package com.quantumai.co2.app

import android.app.Application
import org.koin.core.context.startKoin

class CO2App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            applicationContext
            modules(

            )
        }
    }
}