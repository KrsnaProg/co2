package com.quantumai.co2.di

import com.quantumai.co2.data.SharedRepository
import com.quantumai.co2.data.SharedRepositoryImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val SHARED_MODULE = module {
    single {
        SharedRepositoryImpl(get())
    } bind SharedRepository::class
}
