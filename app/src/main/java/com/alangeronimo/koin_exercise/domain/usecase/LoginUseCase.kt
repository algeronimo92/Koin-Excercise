package com.alangeronimo.koin_exercise.domain.usecase

import com.alangeronimo.koin_exercise.domain.entity.Response
import com.alangeronimo.koin_exercise.domain.repository.AuthRepository
import com.alangeronimo.koin_exercise.network.dto.LoginResponse

class LoginUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(username: String, password: String): Response<LoginResponse> {
        return repository.login(username, password)
    }
}
