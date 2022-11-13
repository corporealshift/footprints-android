package com.corporealshift.footprints.ui.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun Author(avatarURL: String, name: String, url: String = "", modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = rememberAsyncImagePainter(avatarURL),
            contentDescription = name,
            modifier = Modifier.size(48.dp).clip(RoundedCornerShape(50))
        )
        Column(modifier = Modifier.fillMaxWidth().padding(start = 10.dp)) {
            Text(text = name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}