package com.thiago.myapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    var isReady = false
        private set

    fun doSomeWork() = viewModelScope.launch {
        delay(2000L)
        isReady = true
    }
}