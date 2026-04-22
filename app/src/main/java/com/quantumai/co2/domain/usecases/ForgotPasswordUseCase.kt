package com.quantumai.co2.domain.usecases

import com.quantumai.co2.domain.GlobalDataProvider
import com.quantumai.co2.domain.model.ForgotPasswordRequestModel

class ForgotPasswordUseCase(private val globalDataProvider: GlobalDataProvider) {

    suspend operator fun invoke(email: String): String =
        globalDataProvider.forgotPassword(ForgotPasswordRequestModel(emailAddress = email))
}

