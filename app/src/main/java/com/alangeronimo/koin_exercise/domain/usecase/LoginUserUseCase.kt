package com.alangeronimo.koin_exercise.domain.usecase

import com.alangeronimo.koin_exercise.domain.entity.User
import com.alangeronimo.koin_exercise.domain.gateway.dataprovider.LoginUserDataProviderGateway
import com.alangeronimo.koin_exercise.domain.gateway.usecase.LoginUserUseCaseGateway

class LoginUserUseCase(private val loginUserDataProviderGateway: LoginUserDataProviderGateway): LoginUserUseCaseGateway  {

    override fun loginUser(email: String, password: String): User {
        return  loginUserDataProviderGateway.loginUser(email, password)
    }

}