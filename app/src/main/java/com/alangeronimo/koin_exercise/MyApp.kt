@file:OptIn(KoinExperimentalAPI::class)

package com.alangeronimo.koin_exercise

import android.app.Application
import com.alangeronimo.koin_exercise.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.androix.startup.KoinStartup
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.KoinConfiguration

class MyApp : Application(), KoinStartup {

    override fun onKoinStartup() = KoinConfiguration {
        androidLogger()
        // Koin Fragment Factory
        fragmentFactory()
        androidContext(this@MyApp)
        modules(appModule())
    }
}