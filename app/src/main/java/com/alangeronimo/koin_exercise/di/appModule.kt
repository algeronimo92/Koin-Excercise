package com.alangeronimo.koin_exercise.di

import com.alangeronimo.koin_exercise.data.SimpleWorker
import com.alangeronimo.koin_exercise.presentation.viewmodels.MySharedViewModel
import org.koin.androidx.workmanager.dsl.workerOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun appModule() = module {

    viewModelOf(::MySharedViewModel)

    workerOf(::SimpleWorker)
}