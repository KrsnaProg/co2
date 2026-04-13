package com.quantumai.co2.database

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val DATABASE_MODULE = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            CO2Database::class.java,
            "co2_database"
        )
            .fallbackToDestructiveMigration(false)
            .build()
    }

    single { get<CO2Database>().deviceSettingsDao() }
    single { get<CO2Database>().deviceDao() }
}

