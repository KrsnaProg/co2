package com.quantumai.co2.di

import com.quantumai.co2.domain.usecases.ForgotPasswordUseCase
import com.quantumai.co2.domain.usecases.LoginUseCase
import com.quantumai.co2.domain.usecases.RegisterUseCase
import org.koin.dsl.module

val USE_CASES_MODULE = module {
    factory { RegisterUseCase(get()) }
    factory { LoginUseCase(get(), get()) }
    factory { ForgotPasswordUseCase(get()) }
}
