package com.quantumai.co2.domain.usecases

import com.quantumai.co2.data.SharedRepository
import com.quantumai.co2.data.TokenManager

class LoginUseCase(
    private val sharedRepository: SharedRepository,
    private val tokenManager: TokenManager,
) {
    suspend operator fun invoke(email: String, password: String): String {
        val response = sharedRepository.loginUser(email, password)
        tokenManager.saveToken(response.token)
        return response.token
    }
}

