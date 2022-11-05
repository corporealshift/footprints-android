package com.example.footprints.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginScreenModel: ViewModel() {
    private val _loginState = MutableStateFlow(LoginScreenState())

    val loginState: StateFlow<LoginScreenState> = _loginState.asStateFlow()
}