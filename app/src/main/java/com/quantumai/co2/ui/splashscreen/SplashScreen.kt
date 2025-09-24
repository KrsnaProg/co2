package com.quantumai.co2.ui.splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.quantumai.co2.R
import com.quantumai.co2.ui.LoginScreenRoute
import com.quantumai.co2.ui.SplashScreenRoute
import com.quantumai.co2.ui.fonts.Inter
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {

    LaunchedEffect(Unit) {
        delay(2000L)
        navController.navigate(LoginScreenRoute) {
            popUpTo(SplashScreenRoute) { inclusive = true }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.app_logo),
            contentDescription = "Logo",
            modifier = Modifier
        )
        Text(
            text = "Safe Home",
            fontFamily = Inter,
            fontSize = 32.sp,
            lineHeight = 32.sp,
            fontWeight = FontWeight.W700,
            fontStyle = FontStyle.Italic,
            color = Color(0xFF008080),
        )
    }
}