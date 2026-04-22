package com.quantumai.co2.ui.loginscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quantumai.co2.domain.usecases.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class LoginUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null,
)

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _state = MutableStateFlow(LoginUiState())
    val state: StateFlow<LoginUiState> = _state.asStateFlow()

    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _state.value = LoginUiState(errorMessage = "Email and password are required.")
            return
        }
        viewModelScope.launch {
            _state.value = LoginUiState(isLoading = true)
            try {
                loginUseCase(email = email, password = password)
                _state.value = LoginUiState(isSuccess = true)
            } catch (e: Exception) {
                _state.value = LoginUiState(errorMessage = e.message ?: "Login failed. Please try again.")
            }
        }
    }

    fun onNavigationConsumed() {
        _state.value = LoginUiState()
    }

    fun clearError() {
        _state.value = _state.value.copy(errorMessage = null)
    }
}