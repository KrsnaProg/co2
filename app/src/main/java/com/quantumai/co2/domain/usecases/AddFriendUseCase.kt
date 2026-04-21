package com.quantumai.co2.domain.usecases

import com.quantumai.co2.domain.GlobalDataProvider

class AddFriendUseCase(
    private val globalDataProvider: GlobalDataProvider,
) {
    suspend operator fun invoke(nickName: String, phoneNumber: String): String {
        return globalDataProvider.createCustomerFriend(
            nickName = nickName,
            phoneNumber = phoneNumber,
        )
    }
}

