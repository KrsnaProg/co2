package com.quantumai.co2.ui.forgotpasswordscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.quantumai.co2.R
import com.quantumai.co2.ui.CO2Routes
import com.quantumai.co2.ui.colors.AppColors
import com.quantumai.co2.ui.components.CO2Button
import com.quantumai.co2.ui.components.CO2InputField
import com.quantumai.co2.ui.components.CO2TopNavigationBar

@Composable
fun ForgotPasswordScreen(viewModel: ForgotPasswordViewModel,navController: NavController) {

    ForgotPasswordScreenContent(navController)
}

@Composable
private fun ForgotPasswordScreenContent(navController: NavController) {

    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.primaryBackground)
    ) {

        CO2TopNavigationBar(
            title = stringResource(R.string.forgot_password_feature_title),
            onBackClick = {
                navController.navigate(CO2Routes.LoginScreenRoute) {
                    popUpTo(CO2Routes.LoginScreenRoute) { inclusive = true }
                    launchSingleTop = true
                }
            }
        )

        Column(
            modifier = Modifier.padding(start = 24.dp, end = 24.dp)
        ) {

            Spacer(modifier = Modifier.height(33.dp))

            Text(
                text = stringResource(R.string.forgot_password_feature_title),
                fontSize = 24.sp,
                lineHeight = 32.sp,
                fontWeight = FontWeight.Bold,
                color = AppColors.primaryText
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(R.string.forgot_password_feature_receive_code),
                fontSize = 14.sp,
                lineHeight = 20.sp,
                color = AppColors.secondaryText
            )

            Spacer(modifier = Modifier.height(23.dp))

            CO2InputField(
                label = stringResource(R.string.forgot_password_feature_email_address),
                value = email,
                onValueChange = { email = it },
                placeholder = stringResource(R.string.forgot_password_feature_your_email)
            )

            Spacer(modifier = Modifier.height(24.dp))

            CO2Button(
                text = stringResource(R.string.forgot_password_feature_send_code),
                onClick = {
                    navController.navigate(CO2Routes.ResetPasswordScreenRoute){
                        popUpTo(CO2Routes.ForgotPasswordScreenRoute) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}
