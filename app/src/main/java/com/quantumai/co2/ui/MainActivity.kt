package com.quantumai.co2.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.quantumai.co2.R
import com.quantumai.co2.ui.colors.AppColors
import com.quantumai.co2.ui.dashboard.DashboardScreen
import com.quantumai.co2.ui.forgotpasswordscreen.ForgotPasswordScreen
import com.quantumai.co2.ui.loginscreen.LoginScreen
import com.quantumai.co2.ui.registerscreen.RegisterScreen
import com.quantumai.co2.ui.splashscreen.SplashScreen
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = viewModel<MainViewModel>().value
            val state by viewModel.viewState.collectAsState()
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val screens = listOf(
                CO2Routes.SplashScreenRoute,
                CO2Routes.LoginScreenRoute,
                CO2Routes.DashboardScreenRoute
            )

            Scaffold(
                containerColor = Color.White,
                topBar = {
                    var currentScreenRoute: String? by remember {
                        mutableStateOf(null)
                    }
                    LaunchedEffect(navBackStackEntry) {
                        currentScreenRoute =
                            navBackStackEntry?.destination?.route?.substringAfterLast('.')
                    }
                    val currentScreen =
                        screens.find {
                            it.javaClass.simpleName == currentScreenRoute
                        } ?: CO2Routes.SplashScreenRoute
                    if (currentScreen.showTopBar)
                        TopAppBar(
                            title = { Text("Simple Scaffold Screen") },
                            navigationIcon = {
                                IconButton(onClick = { /* "Open nav drawer" */ }) {
                                    Icon(
                                        Icons.Filled.Menu,
                                        contentDescription = "Localized description"
                                    )
                                }
                            }
                        )
                },
                bottomBar = {
                    var currentScreenRoute: String? by remember {
                        mutableStateOf(null)
                    }
                    LaunchedEffect(navBackStackEntry) {
                        currentScreenRoute =
                            navBackStackEntry?.destination?.route?.substringAfterLast('.')
                    }
                    val currentScreen =
                        screens.find {
                            it.javaClass.simpleName == currentScreenRoute
                        } ?: CO2Routes.SplashScreenRoute
                    if (currentScreen.showBottomBar)
                        NavigationBar {
                            val currentDestination =
                                navController.currentBackStackEntryAsState().value?.destination
                            screens.forEach { screen ->
                                NavigationBarItem(
                                    selected = currentDestination?.route == screen.javaClass.simpleName,
                                    onClick = {
                                        //Todo Handle navigation to different screens
                                    },
                                    icon = {
                                        Icon(
                                            painterResource(R.drawable.ic_eye_outlined),
                                            contentDescription = "Hare"
                                        )
                                    },
                                    label = { Text("Krishna") }
                                )
                            }
                        }
                },
            ) { paddingValues ->
                Column(
                    modifier = Modifier.padding(paddingValues)
                ) {
                    NavHost(
                        modifier = Modifier.background(AppColors.primaryBackground),
                        navController = navController,
                        startDestination = CO2Routes.SplashScreenRoute,
                    ) {
                        composable<CO2Routes.SplashScreenRoute> {
                            SplashScreen(navController)
                        }
                        composable<CO2Routes.LoginScreenRoute> {
                            LoginScreen(
                                navController = navController,
                                viewModel = getViewModel()
                            )
                        }
                        composable<CO2Routes.RegisterScreenRoute> {
                            RegisterScreen(
                                navController = navController,
                                viewModel = getViewModel()
                            )
                        }
                        composable<CO2Routes.DashboardScreenRoute> {
                            DashboardScreen(
                                navController = navController,
                                viewModel = getViewModel()
                            )
                        }
                        composable<CO2Routes.ForgotPasswordScreenRoute> {
                            ForgotPasswordScreen(
                                navController = navController,
                                viewModel = getViewModel()
                            )
                        }
                    }
                }
            }
        }
    }
}
