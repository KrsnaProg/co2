package com.quantumai.co2.domain.usecases

import com.quantumai.co2.domain.GlobalDataProvider
import com.quantumai.co2.domain.model.RegisterRequestModel

class RegisterUseCase(private val globalDataProvider: GlobalDataProvider) {

    suspend operator fun invoke(
        fullName: String,
        phoneNumber: String,
        email: String,
        password: String,
    ): String = globalDataProvider.registerUser(
        RegisterRequestModel(
            fullName = fullName,
            phoneNumber = phoneNumber,
            email = email,
            password = password,
        )
    )
}

