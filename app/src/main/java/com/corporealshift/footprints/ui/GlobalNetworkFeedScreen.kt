package com.corporealshift.footprints.ui

import android.content.Context
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.corporealshift.friendica.models.Item
import org.chromium.net.CronetEngine
import java.util.concurrent.Executor
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.core.view.ScrollingView
import com.corporealshift.footprints.ui.posts.TextPostWithComments

@Composable
fun GlobalNetworkFeedScreen(
    modifier: Modifier = Modifier,
    context: Context,
    globalNetworkFeedModel: GlobalNetworkFeedModel = viewModel(),
    engine: CronetEngine,
    executor: Executor,
) {
    globalNetworkFeedModel.loadAllItems(context, engine, executor)
    LazyColumn {
        globalNetworkFeedModel.items.forEach {
            item {
                FeedItem(item = it)
            }
        }
    }
}

@Composable
fun FeedItem(
    item: Item,
) {
    Row() {
        TextPostWithComments(item, modifier = Modifier.padding(horizontal = 0.dp, vertical = 5.dp))
    }
}