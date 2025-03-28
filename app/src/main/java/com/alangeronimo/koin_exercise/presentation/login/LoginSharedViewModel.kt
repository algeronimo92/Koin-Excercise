package com.alangeronimo.koin_exercise.presentation.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.alangeronimo.koin_exercise.domain.entity.Response
import com.alangeronimo.koin_exercise.domain.usecase.LoginUseCase
import com.alangeronimo.koin_exercise.local.UserPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginSharedViewModel(
    private val loginUseCase: LoginUseCase,
    private val userPreferences: UserPreferences,
) : ViewModel() {

    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _uiState = MutableStateFlow<LoginUIState>(LoginUIState.Default)
    val uiState: StateFlow<LoginUIState> = _uiState

    fun onLoginClicked() {
        val user = username.value.orEmpty()
        val pass = password.value.orEmpty()
        Log.d("Alantest MySharedViewModel ${this.hashCode()}", "onLoginClicked()")
        Log.d("Alantest MySharedViewModel ${this.hashCode()}", "user: $user")
        Log.d("Alantest MySharedViewModel ${this.hashCode()}", "pass: $pass")
        login(user, pass)
    }

    fun clearUIState() {
        _uiState.value = LoginUIState.Default
    }

    private fun login(username: String, password: String) {
        if (username.isEmpty() || password.isEmpty()) {
            _uiState.value = LoginUIState.Error("Missing credentials")
            return
        }
        _uiState.value = LoginUIState.Loading
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                loginUseCase(username, password)
            }
            when (result) {
                is Response.Success -> {
                    Log.d("alan LoginSharedViewModel", "login success: ${result.data.accessToken}")
                    userPreferences.saveUserSession(result.data.accessToken ?: "", username)
                    _uiState.value = LoginUIState.Success
                }

                is Response.Failure -> {
                    _uiState.value = LoginUIState.Error(result.error.message.toString())
                    Log.d("alan LoginSharedViewModel", "login failure: " + result.error.toString())
                }
            }
        }
    }
}
