package com.quantumai.co2.domain.usecases

import com.quantumai.co2.data.TokenManager
import com.quantumai.co2.domain.GlobalDataProvider
import com.quantumai.co2.domain.model.RegisterRequestModel

class RegisterUseCase(
    private val globalDataProvider: GlobalDataProvider,
    private val tokenManager: TokenManager,
) {

    suspend operator fun invoke(
        fullName: String,
        phoneNumber: String,
        email: String,
        password: String,
        fcmToken: String,
    ): String {
        val token = globalDataProvider.registerUser(
            RegisterRequestModel(
                fullName = fullName,
                phoneNumber = phoneNumber,
                email = email,
                password = password,
                fcmToken = fcmToken,
            )
        )
        tokenManager.saveToken(token)
        return token
    }
}

