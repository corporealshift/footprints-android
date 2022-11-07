package com.corporealshift.footprints

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.corporealshift.footprints.ui.LoginScreen
import com.corporealshift.footprints.ui.theme.FriendroidTheme
import com.google.android.gms.net.CronetProviderInstaller
import org.chromium.net.CronetEngine

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var cronetBuilder: CronetEngine.Builder
        CronetProviderInstaller.installProvider(this).addOnCompleteListener {
            if (it.isSuccessful) {
                cronetBuilder = CronetEngine.Builder(this).enableHttp2(true).enableQuic(true)
            }
        }
        setContent {
            FriendroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoginScreen(context = this)
                }
            }
        }
    }
}
