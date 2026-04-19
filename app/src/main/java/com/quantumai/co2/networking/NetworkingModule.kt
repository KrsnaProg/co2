package com.quantumai.co2.networking

import com.google.gson.Gson
import com.quantumai.co2.R
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


val NETWORKING_MODULE = module {

    single { Gson() }

    single { AuthInterceptor(get()) }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<AuthInterceptor>())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(androidContext().resources.getString(R.string.base_url))
            .addConverterFactory(ScalarsConverterFactory.create())   // plain-text first
            .addConverterFactory(GsonConverterFactory.create(get())) // JSON second
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get())
            .build()
    }
}