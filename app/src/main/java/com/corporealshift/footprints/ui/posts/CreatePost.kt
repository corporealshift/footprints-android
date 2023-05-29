package com.corporealshift.footprints.ui.posts

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
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
    var photoURIs: List<Uri>? by remember { mutableStateOf(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickMultipleVisualMedia()) { uris ->
        photoURIs = uris
    }

    Column (
        Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
            .verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally) {
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
        if (photoURIs != null) {
            photoURIs!!.map { uri ->
                Log.println(Log.WARN, "doh", "URI: ${uri}");
                val cR: ContentResolver = context.contentResolver
                val type: String? = cR.getType(uri)

                Log.println(Log.WARN, "hiya", "Media Type: ${type}")

                var painter: Painter? = null
                if (type?.startsWith("video") == true) {
                    val mMMR = MediaMetadataRetriever()
                    mMMR.setDataSource(context, uri)
                    val bmp = mMMR.frameAtTime
                    if (bmp != null) {
                        painter = BitmapPainter(bmp.asImageBitmap())
                    }
                } else if (type?.startsWith("image") == true) {
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current).data(data = uri).build()
                    )
                }
                if (painter != null) {
                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(5.dp)
                            .width(150.dp)
                            .height(100.dp)
                            .border(2.0.dp, Color.Gray),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
        Button(
            onClick = {
                      launcher.launch(PickVisualMediaRequest())
            },
        ) {
            Text(text = "Photo/Video")
        }
        Button(
            onClick = {},
        ) {
            Text(text = "Create")
        }
    }
    // media items - media selector?
    // Blog post:
    // https://proandroiddev.com/implementing-photo-picker-on-android-kotlin-jetpack-compose-326e33e83b85
    // Example:
    // https://github.com/tdcolvin/PhotoPickerDemo/blob/master/app/src/main/java/com/hardcoreandroid/photopickerdemo/MainActivity.kt
    // Photos are uploaded separately, then added to posts
}