package com.corporealshift.footprints.ui

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.corporealshift.footprints.R
import com.corporealshift.footprints.api.NetworkPublicTimeline
import com.corporealshift.footprints.prefs.InternalData
import kotlinx.coroutines.Dispatchers
import org.chromium.net.CronetEngine
import java.util.concurrent.Executor

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginScreenModel: LoginScreenModel = viewModel(),
    context: Context,
    engine: CronetEngine,
    executor: Executor,
) {

    Column (
        Modifier.fillMaxSize().padding(start = 20.dp, end = 20.dp, top = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
            ) {
        Text("Welcome to Footprints")
        LoginTextField(
            value = loginScreenModel.host,
            onValueChange = { loginScreenModel.updateHost(it) },
            label = R.string.login_host_label,
            placeholder = R.string.login_host_placeholder,
            onKeyboardDone = {},
        )
        LoginTextField(
            value = loginScreenModel.username,
            onValueChange = { loginScreenModel.updateUsername(it) },
            label = R.string.login_username_label,
            onKeyboardDone = {},
        )
        PasswordField(
            password = loginScreenModel.password,
            onPasswordChange = { loginScreenModel.updatePassword(it) },
            onKeyboardDone = {},
        )
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            Button(
                modifier = modifier.width(160.dp).height(60.dp).padding(top = 10.dp),
                onClick= {
                    onLoginButton(context, loginScreenModel)
                    loginScreenModel.getAllItems(engine, executor, context)
                }
            ) {
                Text(stringResource(R.string.login_submit))
            }
        }
    }
}

fun onLoginButton(context: Context, loginScreenModel: LoginScreenModel) {
    InternalData().saveLoginCredentials(context, loginScreenModel)
}

@Composable
fun LoginTextField(
    onValueChange: (String) -> Unit,
    onKeyboardDone: () -> Unit,
    value: String,
    label: Int,
    placeholder: Int = label,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(label)) },
        placeholder = { Text(stringResource(placeholder)) },
        modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp),
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onDone = { onKeyboardDone() }
        )
    )
}

@Composable
fun PasswordField(
    onPasswordChange: (String) -> Unit,
    onKeyboardDone: () -> Unit,
    password: String,
) {
    var passwordVisible by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text(stringResource(R.string.login_password_label)) },
        placeholder = { Text(stringResource(R.string.login_password_label)) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image = if (passwordVisible)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff
            // Localized description for accessibility services
            val description = if (passwordVisible) stringResource(R.string.login_hide_password) else stringResource(R.string.login_show_password)
            // Toggle button to hide or display password
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector  = image, description)
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { onKeyboardDone() }
        )
    )
}