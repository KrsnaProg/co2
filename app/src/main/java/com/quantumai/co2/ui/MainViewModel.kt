package com.quantumai.co2.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quantumai.co2.domain.usecases.GetMyDeviceInfoUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val getMyDeviceInfoUseCase: GetMyDeviceInfoUseCase,
) : ViewModel() {
    fun getDeviceInfo(deviceId: String) {
        viewModelScope.launch {
            val info = getMyDeviceInfoUseCase.invoke(deviceId)
            Log.d("luka", "Device info: info")
        }
    }
}