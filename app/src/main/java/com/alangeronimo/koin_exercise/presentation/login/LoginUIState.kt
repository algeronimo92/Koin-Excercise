package com.alangeronimo.koin_exercise.presentation.login

sealed class LoginUIState {
    data object Loading : LoginUIState()
    data object Success : LoginUIState()
    data class Error(val message: String) : LoginUIState()
    data object Default: LoginUIState()
}