package com.quantumai.co2.di

import com.quantumai.co2.ui.MainViewModel
import com.quantumai.co2.ui.contactsscreen.ContactsViewModel
import com.quantumai.co2.ui.devicedetailscreen.DeviceDetailViewModel
import com.quantumai.co2.ui.devicesscreen.DevicesViewModel
import com.quantumai.co2.ui.devicesettingsscreen.DeviceSettingsViewModel
import com.quantumai.co2.ui.forgotpasswordscreen.ForgotPasswordViewModel
import com.quantumai.co2.ui.loginscreen.LoginViewModel
import com.quantumai.co2.ui.registerscreen.RegisterViewModel
import com.quantumai.co2.ui.resetpasswordscreen.ResetPasswordViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val VIEW_MODELS_MODULE = module {
    viewModel { MainViewModel() }
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { ForgotPasswordViewModel(get()) }
    viewModel { ResetPasswordViewModel(get()) }
    viewModel { DevicesViewModel(get()) }
    viewModel { ContactsViewModel() }
    viewModel { params -> DeviceDetailViewModel(params.get(), get()) }
    viewModel { params -> DeviceSettingsViewModel(params.get(), get(), get()) }
}