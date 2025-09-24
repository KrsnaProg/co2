package com.quantumai.co2.app

import android.app.Application
import com.quantumai.co2.di.APPLICATION_MODULE
import com.quantumai.co2.di.NETWORKING_MODULE
import com.quantumai.co2.di.USE_CASES_MODULE
import com.quantumai.co2.di.VIEW_MODELS_MODULE
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class CO2App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CO2App)
            modules(
                NETWORKING_MODULE,
                APPLICATION_MODULE,
                USE_CASES_MODULE,
                VIEW_MODELS_MODULE,
            )
        }
    }
}
