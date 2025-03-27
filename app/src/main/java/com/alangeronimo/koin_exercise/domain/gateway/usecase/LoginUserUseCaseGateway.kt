package com.alangeronimo.koin_exercise.domain.gateway.usecase

import com.alangeronimo.koin_exercise.domain.entity.User

interface LoginUserUseCaseGateway {
    fun loginUser(email: String, password: String) : User
}