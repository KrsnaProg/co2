package com.quantumai.co2.domain.usecases

import com.quantumai.co2.domain.GlobalDataProvider
import com.quantumai.co2.domain.model.ResetPasswordRequestModel

class ResetPasswordUseCase(private val globalDataProvider: GlobalDataProvider) {

    suspend operator fun invoke(
        verificationCode: String,
        newPassword: String,
        confirmPassword: String,
    ): String = globalDataProvider.resetPassword(
        ResetPasswordRequestModel(
            verificationCode = verificationCode,
            newPassword = newPassword,
            confirmPassword = confirmPassword,
        )
    )
}

