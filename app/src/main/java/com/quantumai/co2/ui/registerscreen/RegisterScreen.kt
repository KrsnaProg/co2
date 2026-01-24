package com.quantumai.co2.ui.registerscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.quantumai.co2.R
import com.quantumai.co2.ui.colors.AppColors
import com.quantumai.co2.ui.compoonet.CO2InputField
import com.quantumai.co2.ui.fonts.Inter

@Composable
fun RegisterScreen(viewModel: RegisterViewModel,navController: NavController) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(top = 133.dp, start = 24.dp, end = 24.dp, bottom = 120.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(R.drawable.app_logo),
                    contentDescription = "App Logo",
                    modifier = Modifier.size(32.dp)
                )
                Text(
                    text = stringResource(R.string.app_logo_name),
                    fontFamily = Inter,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.W700,
                    fontStyle = FontStyle.Italic,
                    color = AppColors.primaryText
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = stringResource(R.string.register_feature_create_your_account),
                fontFamily = Inter,
                fontSize = 24.sp,
                fontWeight = FontWeight.W700,
                color = AppColors.primaryText
            )
        }

        Spacer(modifier = Modifier.height(31.dp))

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)) {

            // name surname
            var nameSurname by remember { mutableStateOf("") }
            CO2InputField(
                label = stringResource(R.string.register_feature_name_and_surname),
                value = nameSurname,
                onValueChange = { nameSurname = it },
                placeholder = stringResource(R.string.register_feature_name_and_surname_place_holder)
            )

            // Phone
            var phone by remember { mutableStateOf("") }
            CO2InputField(
                label = stringResource(R.string.register_feature_phone_number),
                value = phone,
                onValueChange = { input ->
                    phone = input.filter { it.isDigit() }
                },
                placeholder = stringResource(R.string.register_feature_phone_number_place_holder),
                keyboardType = KeyboardType.Number
            )

            // Email
            var email by remember { mutableStateOf("") }
            CO2InputField(
                label = stringResource(R.string.register_feature_email),
                value = email,
                onValueChange = { email = it },
                placeholder = stringResource(R.string.register_feature_email_place_holder)
            )

            // password
            var password by remember { mutableStateOf("") }
            CO2InputField(
                label = stringResource(R.string.register_feature_password),
                value = password,
                onValueChange = { password = it },
                placeholder = stringResource(R.string.register_feature_password_place_holder),
                isPassword = true
            )
        }

        Button(
            onClick = {
               // sign up click in here
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = AppColors.primaryButton)
        ) {
            Text(
                text = stringResource(R.string.register_feature_sign_up),
                fontFamily = Inter,
                fontSize = 14.sp,
                fontWeight = FontWeight.W500,
                color = Color.White
            )
        }
    }
}


