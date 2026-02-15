package com.quantumai.co2.ui

import androidx.annotation.DrawableRes
import com.quantumai.co2.R
import kotlinx.serialization.Serializable

@Serializable
sealed class CO2Routes(
    val title: String,
    @DrawableRes val icon: Int? = null,
    val showTopBar: Boolean = true,
    val showBottomBar: Boolean = true,
) {
    companion object {
        val all: List<CO2Routes> = listOf(
            SplashScreenRoute,
            LoginScreenRoute,
            DevicesScreenRoute,
            RegisterScreenRoute,
            ForgotPasswordScreenRoute,
            ResetPasswordScreenRoute,
        )

        val bottomTabs: List<CO2Routes> = listOf(
            DevicesScreenRoute,
            // Add more main tabs here later.
        )
    }

    @Serializable
    object SplashScreenRoute : CO2Routes(
        title = "Splash Screen",
        showTopBar = false,
        showBottomBar = false,
    )

    @Serializable
    object LoginScreenRoute : CO2Routes(
        title = "Login",
        showTopBar = false,
        showBottomBar = false
    )

    @Serializable
    object DevicesScreenRoute : CO2Routes(
        title = "Devices",
        icon = R.drawable.nav_devices,
        showTopBar = false,
        showBottomBar = true,
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
        showTopBar = true,
        showBottomBar = false
    )

    @Serializable
    object ResetPasswordScreenRoute : CO2Routes(
        title = "Reset Password",
        icon = null,
        showTopBar = true,
        showBottomBar = false
    )
}
