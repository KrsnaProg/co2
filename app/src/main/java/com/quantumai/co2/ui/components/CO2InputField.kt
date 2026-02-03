package com.quantumai.co2.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.quantumai.co2.R
import com.quantumai.co2.ui.colors.AppColors
import com.quantumai.co2.ui.fonts.Inter

@Composable
fun CO2InputField(
    label: String? = null,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isPassword: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    var passwordVisual by remember { mutableStateOf(true) }

    Column(modifier = Modifier.fillMaxWidth()) {
        label?.let {
            Text(
                text = it,
                fontFamily = Inter,
                fontSize = 16.sp,
                fontWeight = FontWeight.Black,
                fontStyle = FontStyle.Normal,
                color = AppColors.primaryText
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            textStyle = TextStyle(
                fontWeight = FontWeight.W400,
                fontSize = 16.sp,
                color = AppColors.primaryText
            ),
            placeholder = {
                Text(
                    text = placeholder,
                    fontWeight = FontWeight.W400,
                    fontSize = 16.sp,
                    color = AppColors.secondaryText,
                )
            },
            visualTransformation =
                if (isPassword && passwordVisual) PasswordVisualTransformation()
                else VisualTransformation.None,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 53.dp),
            shape = RoundedCornerShape(10.dp),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,

                focusedIndicatorColor = Color(0xFFDEE1E6),
                unfocusedIndicatorColor = Color(0xFFDEE1E6),
                disabledIndicatorColor = Color(0xFFDEE1E6),

                cursorColor = AppColors.primaryText,

                focusedTextColor = AppColors.primaryText,
                unfocusedTextColor = AppColors.primaryText,
                disabledTextColor = Color(0xFF565D6D),

                focusedPlaceholderColor = AppColors.secondaryText,
                unfocusedPlaceholderColor = AppColors.secondaryText
            ),
            trailingIcon = {
                if (!isPassword) return@OutlinedTextField
                val icon = if (passwordVisual)
                    painterResource(id = R.drawable.ic_eye_outlined) // eye open
                else painterResource(id = R.drawable.ic_eye_closed) // eye closed
                IconButton(onClick = { passwordVisual = !passwordVisual }) {
                    Icon(
                        painter = icon,
                        contentDescription = "Toggle password visibility"
                    )
                }
            }
        )
    }
}

@Preview
@Composable
fun CO2InputFieldP() {
    CO2InputField(
        label = "Name and Surname",
        value = "Value",
        onValueChange = {},
        placeholder = "",
        isPassword = true
    )
}