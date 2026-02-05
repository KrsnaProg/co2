package com.quantumai.co2.ui

import androidx.annotation.DrawableRes
import kotlinx.serialization.Serializable

@Serializable
sealed class CO2Routes(
    val title: String,
    @DrawableRes val icon: Int? = null,
    val showTopBar: Boolean = true,
    val showBottomBar: Boolean = true
) {
    @Serializable
    object SplashScreenRoute : CO2Routes(
        title = "Splash Screen",
        showTopBar = false,
        showBottomBar = false
    )

    @Serializable
    object LoginScreenRoute : CO2Routes(
        title = "Login",
        icon = null,
        showTopBar = false,
        showBottomBar = false
    )

    @Serializable
    object DashboardScreenRoute : CO2Routes(
        title = "Dashboard",
        icon = null,
        showTopBar = true,
        showBottomBar = true
    )

    @Serializable
    object RegisterScreenRoute : CO2Routes(
        title = "Register",
        icon = null,
        showTopBar = false,
        showBottomBar = false
    )

    @Serializable
    object ForgotPasswordScreenRoute : CO2Routes(
        title = "Forgot Password",
        icon = null,
        showTopBar = false,
        showBottomBar = false
    )
}
