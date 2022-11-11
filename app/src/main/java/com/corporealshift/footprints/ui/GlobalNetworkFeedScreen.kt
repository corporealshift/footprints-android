package com.corporealshift.footprints.ui

import android.content.Context
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
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
        Text(item.text)
    }
}