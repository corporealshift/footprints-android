package com.corporealshift.footprints

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.corporealshift.footprints.prefs.InternalData
import com.corporealshift.footprints.ui.LoginScreen
import com.corporealshift.footprints.ui.theme.FriendroidTheme
import com.google.android.gms.net.CronetProviderInstaller
import org.chromium.net.CronetEngine
import java.util.concurrent.Executors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.compose.composable
import com.corporealshift.footprints.ui.timelines.HomeTimelineModel
import com.corporealshift.footprints.ui.timelines.TimelineScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CronetProviderInstaller.installProvider(this).addOnCompleteListener { cronetLoaded ->
            if (cronetLoaded.isSuccessful) {
                val cronetBuilder = CronetEngine.Builder(this).enableHttp2(true).enableQuic(true)
                setContent {
                    FriendroidTheme {
                        val initialCreds = InternalData().getCredentials(this)
                        var creds by remember { mutableStateOf(initialCreds) }
                        val navController = rememberNavController()
                        val activity = this
                        // A surface container using the 'background' color from the theme
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colors.background
                        ) {
                            Log.w("CREDS", "Creds: $creds")
                            if (creds?.username!!.isNotEmpty() && creds?.password!!.isNotEmpty()) {
                                NavHost(navController = navController, startDestination = GlobalNetwork.route) {
                                    composable(route = GlobalNetwork.route) {
                                        TimelineScreen(
                                            context = activity,
                                            engine = cronetBuilder.build(),
                                            executor = Executors.newSingleThreadExecutor(),
                                            timelineModel = HomeTimelineModel(),
                                        )
                                    }
                                }
                            } else {
                                LoginScreen(
                                    context = activity,
                                    onCredentialsSaved = { creds = it }
                                )
                            }
                        }
                    }
                }
            } else {
                Log.println(Log.ERROR, "doh", cronetLoaded.exception.toString());
            }
        }

    }
}
