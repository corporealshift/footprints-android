package com.example.footprints.ui

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginScreenModel: ViewModel() {
    var host by mutableStateOf("")
        private set
    var username by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    fun updateHost(newHost: String) {
        host = newHost
    }
    fun updateUsername(newUsername: String) {
        username = newUsername
    }
    fun updatePassword(newPassword: String) {
        password = newPassword
    }
}