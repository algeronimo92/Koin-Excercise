package com.alangeronimo.koin_exercise.presentation.viewmodels

import androidx.lifecycle.ViewModel

class MySharedViewModel : ViewModel() {

    fun session(): String{
        return this.hashCode().toString()
    }

    fun performLogin() {

    }

}