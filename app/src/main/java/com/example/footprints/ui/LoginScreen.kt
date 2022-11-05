package com.example.footprints.ui

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginScreenModel: LoginScreenModel = viewModel(),
) {
    val loginScreenState by loginScreenModel.loginState.collectAsState()
    Column {
        Text(text = "Footprints")
        TextField(value = loginScreenState.host, onValueChange = {})
        TextField(value = loginScreenState.username, onValueChange = {})
        TextField(value = loginScreenState.password, onValueChange = {})
    }
}