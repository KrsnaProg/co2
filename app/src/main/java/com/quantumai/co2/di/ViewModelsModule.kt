package com.quantumai.co2.di

import com.quantumai.co2.ui.MainViewModel
import com.quantumai.co2.ui.loginscreen.LoginViewModel
import com.quantumai.co2.ui.registerscreen.RegisterViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val VIEW_MODELS_MODULE = module {
    viewModel {
        MainViewModel(get())
    }

    viewModel {
        LoginViewModel()
    }

    viewModel {
        RegisterViewModel()
    }
}