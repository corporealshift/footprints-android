package com.corporealshift.footprints.ui.posts

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.corporealshift.footprints.ui.timelines.TimelineModel
import org.chromium.net.CronetEngine
import java.util.concurrent.Executor

@Composable
fun CreatePostScreen(
    modifier: Modifier = Modifier,
    context: Context,
    engine: CronetEngine,
    executor: Executor,
) {
    // Parts of the create screen:
    var title by remember { mutableStateOf("") }
    var postBody by remember { mutableStateOf("") }
    Column (Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = postBody,
            onValueChange = { postBody = it },
            label = { Text("Post") },
            singleLine = false,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Send
            )
        )
    }
    // media items - media selector?
    // Blog post:
    // https://proandroiddev.com/implementing-photo-picker-on-android-kotlin-jetpack-compose-326e33e83b85
    // Example:
    // https://github.com/tdcolvin/PhotoPickerDemo/blob/master/app/src/main/java/com/hardcoreandroid/photopickerdemo/MainActivity.kt
    // Photos are uploaded separately, then added to posts
}