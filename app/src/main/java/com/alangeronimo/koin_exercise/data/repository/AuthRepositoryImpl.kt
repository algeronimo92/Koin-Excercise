package com.alangeronimo.koin_exercise.data.repository

import com.alangeronimo.koin_exercise.domain.entity.Response
import com.alangeronimo.koin_exercise.domain.repository.AuthRepository
import com.alangeronimo.koin_exercise.network.AuthApi
import com.alangeronimo.koin_exercise.network.dto.LoginRequest
import com.alangeronimo.koin_exercise.network.dto.LoginResponse
import retrofit2.HttpException

class AuthRepositoryImpl(private val api: AuthApi) : AuthRepository {
    override suspend fun login(username: String, password: String): Response<LoginResponse> {
        return try {
            val response = api.login(LoginRequest(username, password))
            Response.Success(response)
        } catch (e: HttpException) {
            Response.Failure(Error(e.message(), e.cause))
        } catch (e: Exception) {
            Response.Failure(Error(e.message, e.cause))
        }
    }
}