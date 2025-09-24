package com.quantumai.co2.di

import com.quantumai.co2.domain.usecases.GetMyDeviceInfoUseCase
import org.koin.dsl.module

val USE_CASES_MODULE = module {
    factory {
        GetMyDeviceInfoUseCase(get())
    }
}
