package com.quantumai.co2.ui.registerscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quantumai.co2.domain.usecases.RegisterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class RegisterUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null,
)

class RegisterViewModel(private val registerUseCase: RegisterUseCase) : ViewModel() {

    private val _state = MutableStateFlow(RegisterUiState())
    val state: StateFlow<RegisterUiState> = _state.asStateFlow()

    fun register(fullName: String, phoneNumber: String, email: String, password: String) {
        if (fullName.isBlank() || phoneNumber.isBlank() || email.isBlank() || password.isBlank()) {
            _state.value = RegisterUiState(errorMessage = "All fields are required.")
            return
        }
        viewModelScope.launch {
            _state.value = RegisterUiState(isLoading = true)
            try {
                registerUseCase(
                    fullName = fullName,
                    phoneNumber = phoneNumber,
                    email = email,
                    password = password,
                )
                _state.value = RegisterUiState(isSuccess = true)
            } catch (e: Exception) {
                _state.value = RegisterUiState(errorMessage = e.message ?: "Registration failed. Please try again.")
            }
        }
    }

    fun clearError() {
        _state.value = _state.value.copy(errorMessage = null)
    }

    /** Call this immediately after consuming the success navigation event. */
    fun onNavigationConsumed() {
        _state.value = RegisterUiState()
    }
}
