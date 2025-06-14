package com.quantumai.co2.ui

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quantumai.co2.domain.usecases.GetMyDeviceInfoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val getMyDeviceInfoUseCase: GetMyDeviceInfoUseCase,
) : ViewModel() {

    private val _viewState = MutableStateFlow(MainViewState())
    val viewState: StateFlow<MainViewState> = _viewState.asStateFlow()


    fun getDeviceInfo(deviceId: String) {
        viewModelScope.launch {
            val info = getMyDeviceInfoUseCase.invoke(deviceId)

            _viewState.update { it.copy(deviceData = info) }

            Log.d("luka", "Device info: info $info")
        }

    }
}