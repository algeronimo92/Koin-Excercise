package com.alangeronimo.koin_exercise.di

import com.alangeronimo.koin_exercise.data.repository.AuthRepositoryImpl
import com.alangeronimo.koin_exercise.domain.repository.AuthRepository
import com.alangeronimo.koin_exercise.domain.usecase.LoginUseCase
import com.alangeronimo.koin_exercise.network.AuthApi
import com.alangeronimo.koin_exercise.presentation.viewmodel.LoginSharedViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://dummyjson.com/") // 🔁 cambia por tu URL real
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<AuthApi> { get<Retrofit>().create(AuthApi::class.java) }
}

val dataModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) } // get() trae el AuthApi
}

val domainModule = module {
    factory { LoginUseCase(get()) } // get() trae el AuthRepository
}

val presentationModule = module {
    viewModel { LoginSharedViewModel(get()) } // get() trae el LoginUseCase
}