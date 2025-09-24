package com.quantumai.co2.di

import com.quantumai.co2.ui.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val VIEW_MODELS_MODULE = module {
    viewModel {
        MainViewModel(get())
    }
}