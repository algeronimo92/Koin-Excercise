package com.alangeronimo.koin_exercise.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alangeronimo.koin_exercise.domain.entity.Response
import com.alangeronimo.koin_exercise.domain.usecase.LoginUseCase
import com.alangeronimo.koin_exercise.network.dto.LoginResponse
import kotlinx.coroutines.launch

class LoginSharedViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {

    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    fun session(): String {
        return this.hashCode().toString()
    }

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> get() = _loginResult

    fun onLoginClicked() {
        val user = username.value.orEmpty()
        val pass = password.value.orEmpty()
        Log.d("Alantest MySharedViewModel ${this.hashCode()}", "onLoginClicked()")
        Log.d("Alantest MySharedViewModel ${this.hashCode()}", "user: $user")
        Log.d("Alantest MySharedViewModel ${this.hashCode()}", "pass: $pass")
        login(user, pass)
    }

    private fun login(username: String, password: String) {
        viewModelScope.launch {
            when (val response: Response<LoginResponse> = loginUseCase(username, password)) {
                is Response.Success -> {
                    _loginResult.postValue(true)
                }

                is Response.Failure -> {
                    _loginResult.postValue(false)
                    Log.d("alan failure", response.error.toString())
                }
            }
        }
    }
}
