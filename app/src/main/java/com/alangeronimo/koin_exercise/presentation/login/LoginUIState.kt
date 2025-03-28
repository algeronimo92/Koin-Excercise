package com.alangeronimo.koin_exercise.presentation.login

sealed class LoginUIState {
    data object Loading : LoginUIState()
    data object Success : LoginUIState()
    sealed class Error(val message: String) : LoginUIState() {
        data object MissingCredentials : Error("Missing Credential")
        data object MissingUser : Error("Missing User")
        data object MissingPassword : Error("Missing Password")
        data object LoginFailed : Error("LoginFailed")
    }
    data object Default: LoginUIState()
}