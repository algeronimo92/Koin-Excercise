@file:OptIn(KoinExperimentalAPI::class)

package com.alangeronimo.koin_exercise

import android.app.Application
import com.alangeronimo.koin_exercise.di.dataModule
import com.alangeronimo.koin_exercise.di.domainModule
import com.alangeronimo.koin_exercise.di.localModule
import com.alangeronimo.koin_exercise.di.networkModule
import com.alangeronimo.koin_exercise.di.presentationModule
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
        modules(
            listOf(
                networkModule,
                dataModule,
                domainModule,
                presentationModule,
                localModule
            )
        )
    }
}