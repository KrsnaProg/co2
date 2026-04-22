package com.quantumai.co2.ui.loginscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.quantumai.co2.R
import com.quantumai.co2.ui.CO2Routes
import com.quantumai.co2.ui.colors.AppColors
import com.quantumai.co2.ui.components.CO2Button
import com.quantumai.co2.ui.components.CO2InputField
import com.quantumai.co2.ui.fonts.Inter

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel
) {
    val state by viewModel.state.collectAsState()
    val scrollState = rememberScrollState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Navigate to Devices on success — reset state first to avoid re-trigger
    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            viewModel.onNavigationConsumed()
            navController.navigate(CO2Routes.DevicesScreenRoute) {
                popUpTo(CO2Routes.LoginScreenRoute) { inclusive = true }
                launchSingleTop = true
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(top = 43.dp)
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Image(
                painter = painterResource(R.drawable.app_logo),
                contentDescription = "Logo",
                modifier = Modifier.size(32.dp),
            )
            Text(
                text = stringResource(R.string.app_logo_name),
                fontFamily = Inter,
                fontSize = 25.sp,
                fontWeight = FontWeight.W700,
                fontStyle = FontStyle.Italic,
                color = AppColors.primaryGreen,
            )
        }

        Spacer(modifier = Modifier.height(80.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .aspectRatio(0.54f),
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
            colors = CardDefaults.cardColors(containerColor = AppColors.primaryBackground),
            border = BorderStroke(1.dp, Color(0x171a1f14))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.log_in_feature_title),
                    fontFamily = Inter,
                    fontSize = 30.sp,
                    lineHeight = 36.sp,
                    fontWeight = FontWeight.W700,
                    color = AppColors.primaryText,
                )

                Spacer(modifier = Modifier.height(24.dp))

                CO2InputField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = stringResource(R.string.log_in_feature_entry_email),
                )

                Spacer(modifier = Modifier.height(20.dp))

                CO2InputField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = stringResource(R.string.log_in_feature_entry_password),
                    isPassword = true,
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Error message
                state.errorMessage?.let { error ->
                    Text(
                        text = error,
                        color = AppColors.errorText,
                        fontSize = 13.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                if (state.isLoading) {
                    CircularProgressIndicator(
                        color = AppColors.primaryGreen,
                        modifier = Modifier.size(44.dp)
                    )
                } else {
                    CO2Button(
                        text = stringResource(R.string.log_in_feature_log_in_button),
                        onClick = {
                            viewModel.clearError()
                            viewModel.login(email = email, password = password)
                        },
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    modifier = Modifier.clickable(
                        interactionSource = null,
                        indication = null
                    ) {
                        navController.navigate(CO2Routes.ForgotPasswordScreenRoute)
                    },
                    text = stringResource(R.string.log_in_feature_forget_password),
                    fontFamily = Inter,
                    fontSize = 14.sp,
                    lineHeight = 22.sp,
                    fontWeight = FontWeight.W500,
                    color = AppColors.secondaryText,
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.log_in_feature_dont_have_account),
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = stringResource(R.string.log_in_feature_sign_up),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = AppColors.primaryGreen,
                        modifier = Modifier.clickable {
                            navController.navigate(CO2Routes.RegisterScreenRoute)
                        }
                    )
                }
            }
        }
    }
}
