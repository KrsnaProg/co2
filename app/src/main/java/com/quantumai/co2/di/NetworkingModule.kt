package com.quantumai.co2.di

import com.google.gson.Gson
import com.quantumai.co2.R
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


val NETWORKING_MODULE = module {

    single {
        Gson()
    }

    single {
        OkHttpClient
            .Builder()
            .build()
    }

    single {
        RxJava2CallAdapterFactory.create()
    } bind CallAdapter.Factory::class

    single {
        GsonConverterFactory.create(get())
    } bind Converter.Factory::class

    single {
        Retrofit.Builder()
            .baseUrl(androidContext().resources.getString(R.string.base_url))
            .addConverterFactory(get())
            .addCallAdapterFactory(get())
            .client(get())
            .build()
    }
}