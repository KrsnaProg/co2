package com.quantumai.co2.ui.resetpasswordscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.quantumai.co2.R
import com.quantumai.co2.ui.CO2Routes
import com.quantumai.co2.ui.colors.AppColors
import com.quantumai.co2.ui.components.CO2Button
import com.quantumai.co2.ui.components.CO2InputField

@Composable
fun ResetPasswordScreen(viewModel: ResetPasswordViewModel, navController: NavController) {

    val state by viewModel.state.collectAsState()

    var codeDigits by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    // Navigate to Login on success
    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            viewModel.onNavigationConsumed()
            navController.navigate(CO2Routes.LoginScreenRoute) {
                popUpTo(CO2Routes.ForgotPasswordScreenRoute) { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.primaryBackground)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 100.dp)
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Column(
            modifier = Modifier.padding(start = 24.dp, end = 24.dp)
        ) {
            CO2InputField(
                textFieldModifier = Modifier.height(53.dp),
                label = stringResource(R.string.reset_password_feature_verification_code),
                value = codeDigits,
                onValueChange = { codeDigits = it.filter { c -> c.isDigit() } },
                placeholder = stringResource(R.string.reset_password_feature_digit_code),
                keyboardType = KeyboardType.Number
            )

            Spacer(modifier = Modifier.height(24.dp))

            CO2InputField(
                label = stringResource(R.string.reset_password_feature_new_password),
                value = newPassword,
                onValueChange = { newPassword = it },
                placeholder = stringResource(R.string.reset_password_feature_enter_new_password),
                isPassword = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            CO2InputField(
                label = stringResource(R.string.reset_password_feature_confirm_password),
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                placeholder = stringResource(R.string.reset_password_feature_re_enter_password),
                isPassword = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Error message
            state.errorMessage?.let { error ->
                Text(
                    text = error,
                    color = AppColors.errorText,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            if (state.isLoading) {
                CircularProgressIndicator(
                    color = AppColors.primaryGreen,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else {
                CO2Button(
                    text = stringResource(R.string.reset_password_feature_sign_in),
                    onClick = {
                        viewModel.clearError()
                        viewModel.resetPassword(
                            verificationCode = codeDigits,
                            newPassword = newPassword,
                            confirmPassword = confirmPassword,
                        )
                    }
                )
            }
        }
    }
}
