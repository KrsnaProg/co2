package com.quantumai.co2.ui.splashscreen

import androidx.lifecycle.ViewModel
import com.quantumai.co2.data.TokenManager

class SplashViewModel(
    private val tokenManager: TokenManager,
) : ViewModel() {
    fun isLoggedIn(): Boolean = tokenManager.isLoggedIn()
}
