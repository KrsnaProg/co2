package com.quantumai.co2.ui.resetpasswordscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quantumai.co2.domain.usecases.ResetPasswordUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ResetPasswordUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null,
)

class ResetPasswordViewModel(
    private val resetPasswordUseCase: ResetPasswordUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(ResetPasswordUiState())
    val state: StateFlow<ResetPasswordUiState> = _state.asStateFlow()

    fun resetPassword(
        verificationCode: String,
        newPassword: String,
        confirmPassword: String,
    ) {
        when {
            verificationCode.isBlank() -> {
                _state.value = ResetPasswordUiState(errorMessage = "Verification code is required.")
                return
            }
            newPassword.isBlank() -> {
                _state.value = ResetPasswordUiState(errorMessage = "New password is required.")
                return
            }
            newPassword != confirmPassword -> {
                _state.value = ResetPasswordUiState(errorMessage = "Passwords do not match.")
                return
            }
        }
        viewModelScope.launch {
            _state.value = ResetPasswordUiState(isLoading = true)
            try {
                resetPasswordUseCase(
                    verificationCode = verificationCode,
                    newPassword = newPassword,
                    confirmPassword = confirmPassword,
                )
                _state.value = ResetPasswordUiState(isSuccess = true)
            } catch (e: Exception) {
                _state.value = ResetPasswordUiState(errorMessage = e.message ?: "Failed to reset password. Please try again.")
            }
        }
    }

    fun onNavigationConsumed() {
        _state.value = ResetPasswordUiState()
    }

    fun clearError() {
        _state.value = _state.value.copy(errorMessage = null)
    }
}
