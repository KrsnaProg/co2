package com.quantumai.co2.data

import com.quantumai.co2.domain.GlobalDataProvider
import com.quantumai.co2.domain.model.LoginRequestModel
import com.quantumai.co2.domain.model.LoginResponseModel

class SharedRepositoryImpl(
    private val globalDataProvider: GlobalDataProvider
) : SharedRepository {
    override suspend fun loginUser(
        email: String,
        password: String
    ): LoginResponseModel {
        return globalDataProvider.loginUser(LoginRequestModel(email = email, password = password))
    }
}
