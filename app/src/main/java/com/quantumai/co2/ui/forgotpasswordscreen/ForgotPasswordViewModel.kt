package com.quantumai.co2.ui.forgotpasswordscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quantumai.co2.domain.usecases.ForgotPasswordUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ForgotPasswordUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null,
)

class ForgotPasswordViewModel(
    private val forgotPasswordUseCase: ForgotPasswordUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(ForgotPasswordUiState())
    val state: StateFlow<ForgotPasswordUiState> = _state.asStateFlow()

    fun sendCode(email: String) {
        if (email.isBlank()) {
            _state.value = ForgotPasswordUiState(errorMessage = "Email address is required.")
            return
        }
        viewModelScope.launch {
            _state.value = ForgotPasswordUiState(isLoading = true)
            try {
                forgotPasswordUseCase(email)
                _state.value = ForgotPasswordUiState(isSuccess = true)
            } catch (e: Exception) {
                _state.value = ForgotPasswordUiState(errorMessage = e.message ?: "Failed to send code. Please try again.")
            }
        }
    }

    fun onNavigationConsumed() {
        _state.value = ForgotPasswordUiState()
    }

    fun clearError() {
        _state.value = _state.value.copy(errorMessage = null)
    }
}
