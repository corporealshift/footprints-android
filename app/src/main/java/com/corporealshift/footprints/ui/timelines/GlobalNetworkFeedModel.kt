package com.corporealshift.footprints.ui.timelines

import android.content.Context
import com.corporealshift.footprints.api.NetworkPublicTimeline
import com.corporealshift.friendica.models.Item
import org.chromium.net.CronetEngine
import java.util.concurrent.Executor

class GlobalNetworkFeedModel(private val hostname: String): TimelineModel() {
    override suspend fun networkItems(
        context: Context,
        engine: CronetEngine,
        executor: Executor): ArrayList<Item> {
        return NetworkPublicTimeline(engine, executor, context, hostname).getItems()
    }
}