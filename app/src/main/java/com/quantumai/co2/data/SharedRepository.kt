package com.quantumai.co2.data

import com.quantumai.co2.domain.GlobalDataProvider
import com.quantumai.co2.domain.model.LoginResponseModel

interface SharedRepository {
    suspend fun loginUser(email: String, password: String): LoginResponseModel
}
