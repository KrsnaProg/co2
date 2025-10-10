package com.quantumai.co2.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.quantumai.co2.ui.colors.AppColors
import com.quantumai.co2.ui.loginscreen.LoginScreen
import com.quantumai.co2.ui.splashscreen.SplashScreen
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = viewModel<MainViewModel>().value
            val state by viewModel.viewState.collectAsState()

            val navController = rememberNavController()

            NavHost(
                modifier = Modifier.background(AppColors.primaryBackground),
                navController = navController,
                startDestination = SplashScreenRoute,
            ) {
                composable<SplashScreenRoute> {
                    SplashScreen(navController)
                }
                composable<LoginScreenRoute> {
                    LoginScreen()
                }
            }
        }
    }
}
