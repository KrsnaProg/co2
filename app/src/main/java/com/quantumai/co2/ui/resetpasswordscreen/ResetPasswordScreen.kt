package com.quantumai.co2.ui.resetpasswordscreen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.quantumai.co2.R
import com.quantumai.co2.ui.CO2Routes
import com.quantumai.co2.ui.colors.AppColors
import com.quantumai.co2.ui.components.CO2Button
import com.quantumai.co2.ui.components.CO2InputField
import com.quantumai.co2.ui.components.CO2TopNavigationBar
import com.quantumai.co2.ui.registerscreen.RegisterViewModel


@Composable
fun ResetPasswordScreen(viewModel: RegisterViewModel, navController: NavController){

    ResetPasswordScreenContent(navController)

}

@Composable
fun ResetPasswordScreenContent(navController: NavController){

    var codeDigits by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.primaryBackground)
    ) {
        CO2TopNavigationBar(
            title = stringResource(R.string.reset_password_feature_title),
            onBackClick = {
                navController.navigate(CO2Routes.ForgotPasswordScreenRoute) {
                    popUpTo(CO2Routes.ResetPasswordScreenRoute) { inclusive = true }
                    launchSingleTop = true
                }
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        Column(
            modifier = Modifier.padding(start = 24.dp, end = 24.dp)
        ) {

            CO2InputField(
                label = stringResource(R.string.reset_password_feature_verification_code),
                value = codeDigits,
                onValueChange = { codeDigits = it },
                placeholder = stringResource(R.string.reset_password_feature_digit_code)
            )

            Spacer(modifier = Modifier.height(24.dp))

            CO2InputField(
                label = stringResource(R.string.reset_password_feature_new_password),
                value = newPassword,
                onValueChange = { newPassword = it },
                placeholder = stringResource(R.string.reset_password_feature_enter_new_password)
            )

            Spacer(modifier = Modifier.height(24.dp))

            CO2InputField(
                label = stringResource(R.string.reset_password_feature_confirm_password),
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                placeholder = stringResource(R.string.reset_password_feature_re_enter_password)
            )

            Spacer(modifier = Modifier.height(40.dp))

            CO2Button(
                text = stringResource(R.string.reset_password_feature_sign_in),
                onClick = {
                    //login in account
                }
            )
        }
    }
}
