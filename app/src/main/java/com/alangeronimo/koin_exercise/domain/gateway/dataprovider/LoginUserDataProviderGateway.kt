package com.alangeronimo.koin_exercise.domain.gateway.dataprovider

import com.alangeronimo.koin_exercise.domain.entity.User

interface LoginUserDataProviderGateway {

    fun  loginUser(email: String, password: String) : User
}
