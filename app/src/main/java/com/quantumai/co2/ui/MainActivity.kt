package com.quantumai.co2.ui

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.quantumai.co2.ui.addnewdevicescreen.AddNewDeviceScreen
import com.quantumai.co2.ui.alertsscreen.AlertsScreen
import com.quantumai.co2.ui.colors.AppColors
import com.quantumai.co2.ui.components.CO2TopNavigationBar
import com.quantumai.co2.ui.contactsscreen.ContactsScreen
import com.quantumai.co2.ui.devicedetailscreen.DeviceDetailScreen
import com.quantumai.co2.ui.devicesettingsscreen.DeviceSettingsScreen
import com.quantumai.co2.ui.devicesscreen.DevicesScreen
import com.quantumai.co2.ui.forgotpasswordscreen.ForgotPasswordScreen
import com.quantumai.co2.ui.loginscreen.LoginScreen
import com.quantumai.co2.ui.registerscreen.RegisterScreen
import com.quantumai.co2.ui.resetpasswordscreen.ResetPasswordScreen
import com.quantumai.co2.ui.splashscreen.SplashScreen
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val view = LocalView.current

            val currentDestination = navBackStackEntry?.destination
            val currentRoute = currentDestination?.route
            val currentScreen = CO2Routes.all.firstOrNull { route ->
                currentRoute?.contains(route.javaClass.simpleName) == true
            } ?: CO2Routes.SplashScreenRoute

            //dark icons on white bk
            if (!view.isInEditMode) {
                SideEffect {
                    val window = (view.context as Activity).window
                    WindowInsetsControllerCompat(window, view).isAppearanceLightStatusBars = true
                }
            }

            Scaffold(
                containerColor = Color.White,
                contentWindowInsets = WindowInsets(0),
                topBar = {
                    if (currentScreen.showTopBar) {
                        CO2TopNavigationBar(
                            title = currentScreen.title,
                            onBackClick = {
                                navController.navigateUp()
                            }
                        )
                    }
                },
                bottomBar = {
                    if (currentScreen.showBottomBar) {
                        NavigationBar {
                            CO2Routes.bottomTabs.forEach { screen ->
                                val selected = currentDestination
                                    ?.hierarchy
                                    ?.any { dest -> dest.route?.endsWith(screen.javaClass.simpleName) == true }
                                    ?: false

                                NavigationBarItem(
                                    selected = selected,
                                    onClick = {
                                        // navigate if we are not there
                                        if (!selected) {
                                            navController.navigate(screen) {
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }
                                    },
                                    icon = {
                                        screen.icon?.let {
                                            Icon(
                                                painterResource(it),
                                                contentDescription = null,
                                                modifier = Modifier.size(24.dp),
                                                tint = Color.Unspecified
                                            )
                                        }
                                    },
                                    label = { Text(screen.title) },
                                )
                            }
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
                        //transition off
                        enterTransition = { EnterTransition.None },
                        exitTransition = { ExitTransition.None },
                        popEnterTransition = { EnterTransition.None },
                        popExitTransition = { ExitTransition.None }
                    ) {
                        composable<CO2Routes.SplashScreenRoute> {
                            SplashScreen(
                                navController = navController,
                                viewModel = getViewModel()
                            )
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
                        composable<CO2Routes.DevicesScreenRoute> {
                            DevicesScreen(
                                navController = navController,
                                viewModel = getViewModel()
                            )
                        }
                        composable<CO2Routes.AlertsScreenRoute> {
                            AlertsScreen()
                        }
                        composable<CO2Routes.AddNewDeviceScreenRoute> {
                            AddNewDeviceScreen(
                                navController = navController,
                                viewModel = getViewModel()
                            )
                        }
                        composable<CO2Routes.DeviceDetailScreenRoute> { backStackEntry ->
                            val route = backStackEntry.toRoute<CO2Routes.DeviceDetailScreenRoute>()
                            DeviceDetailScreen(
                                navController = navController,
                                viewModel = getViewModel { parametersOf(route.deviceId) }
                            )
                        }
                        composable<CO2Routes.DeviceSettingsScreenRoute> { backStackEntry ->
                            val route =
                                backStackEntry.toRoute<CO2Routes.DeviceSettingsScreenRoute>()
                            DeviceSettingsScreen(
                                navController = navController,
                                viewModel = getViewModel { parametersOf(route.deviceId) }
                            )
                        }
                        composable<CO2Routes.ContactsScreenRoute> {
                            ContactsScreen(
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
                        composable<CO2Routes.ResetPasswordScreenRoute> {
                            ResetPasswordScreen(
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