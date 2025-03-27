package com.alangeronimo.koin_exercise.network

import com.alangeronimo.koin_exercise.network.dto.LoginRequest
import com.alangeronimo.koin_exercise.network.dto.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("user/login") // ← Esto se concatena al baseUrl
    suspend fun login(@Body request: LoginRequest): LoginResponse
}