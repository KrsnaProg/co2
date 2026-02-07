package com.quantumai.co2.di

import com.quantumai.co2.ui.MainViewModel
import com.quantumai.co2.ui.forgotpasswordscreen.ForgotPasswordViewModel
import com.quantumai.co2.ui.loginscreen.LoginViewModel
import com.quantumai.co2.ui.registerscreen.RegisterViewModel
import com.quantumai.co2.ui.resetpasswordscreen.ResetPasswordViewModel
import org.koin.core.module.dsl.viewModel
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

    viewModel {
        ForgotPasswordViewModel()
    }

    viewModel {
        ResetPasswordViewModel()
    }
}