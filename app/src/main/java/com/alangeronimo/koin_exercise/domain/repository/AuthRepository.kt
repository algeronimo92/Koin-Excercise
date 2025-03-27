package com.alangeronimo.koin_exercise.domain.repository

import com.alangeronimo.koin_exercise.domain.entity.Response
import com.alangeronimo.koin_exercise.network.dto.LoginResponse

interface AuthRepository {
    suspend fun login(username: String, password: String): Response<LoginResponse>
}