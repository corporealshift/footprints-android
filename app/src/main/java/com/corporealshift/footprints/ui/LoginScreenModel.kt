package com.corporealshift.footprints.ui

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corporealshift.footprints.api.NetworkPublicTimeline
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.chromium.net.CronetEngine
import java.util.concurrent.Executor

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

    fun getAllItems(engine: CronetEngine, executor: Executor, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val items = NetworkPublicTimeline(engine, executor, context).getItems()
            Log.println(Log.WARN, "deets", items.toString())
        }
    }
}