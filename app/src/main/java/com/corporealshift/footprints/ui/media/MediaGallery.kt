package com.corporealshift.footprints.ui.media

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.corporealshift.friendica.models.Media
import com.corporealshift.friendica.models.Photo
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MediaGallery(media: ArrayList<Media>, modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState()
    Column {
        HorizontalPager(count = media.size, state = pagerState) { page ->
            when (val mediaItem = media[page]) {
                is Photo ->
                    Card(modifier = Modifier.fillMaxWidth().height(400.dp)) {
                        Box {
                            Image(
                                painter = rememberAsyncImagePainter(mediaItem.url),
                                contentDescription = mediaItem.caption,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                else -> Card {
                    Log.w("Empty", "render blank")
                }
            }
        }
        if (media.size > 1) {
            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp),
            )
        }
    }
}