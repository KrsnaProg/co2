package com.quantumai.co2.di

import android.content.Context
import com.quantumai.co2.data.TokenManager
import com.quantumai.co2.domain.GlobalDataProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit


val APPLICATION_MODULE = module {
    single {
        androidContext().resources
    }

    single {
        get<Retrofit>().create(GlobalDataProvider::class.java)
    }

    single {
        androidContext().getSharedPreferences("co2_prefs", Context.MODE_PRIVATE)
    }

    single {
        TokenManager(get())
    }
}
