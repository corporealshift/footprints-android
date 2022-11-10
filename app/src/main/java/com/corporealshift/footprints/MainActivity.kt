package com.corporealshift.footprints

import android.os.Bundle
import android.util.Log
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
import java.util.concurrent.Executors

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CronetProviderInstaller.installProvider(this).addOnCompleteListener {
            if (it.isSuccessful) {
                val cronetBuilder = CronetEngine.Builder(this).enableHttp2(true).enableQuic(true)
                setContent {
                    FriendroidTheme {
                        // A surface container using the 'background' color from the theme
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colors.background
                        ) {
                            LoginScreen(context = this, engine = cronetBuilder.build(), executor = Executors.newSingleThreadExecutor())
                        }
                    }
                }
            } else {
                Log.println(Log.ERROR, "doh", it.exception.toString());
            }
        }

    }
}
