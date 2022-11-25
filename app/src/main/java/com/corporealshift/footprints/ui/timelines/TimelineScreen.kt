package com.corporealshift.footprints.ui.timelines

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.corporealshift.friendica.models.Item
import org.chromium.net.CronetEngine
import java.util.concurrent.Executor
import androidx.compose.ui.unit.dp
import com.corporealshift.footprints.ui.posts.ResharedPost
import com.corporealshift.footprints.ui.posts.TextPostWithComments
import com.corporealshift.friendica.models.ActivityType

@Composable
fun TimelineScreen(
    modifier: Modifier = Modifier,
    context: Context,
    timelineModel: TimelineModel = viewModel(),
    engine: CronetEngine,
    executor: Executor,
) {
    timelineModel.loadAllItems(context, engine, executor)
    LazyColumn {
        timelineModel.items.forEach {
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
    val rowModifier = Modifier.padding(horizontal = 0.dp, vertical = 5.dp)
    Row() {
        when(item.activityType) {
            ActivityType.RESHARE -> ResharedPost(item, modifier = rowModifier)
            else -> TextPostWithComments(item, modifier = rowModifier)
        }
    }
}