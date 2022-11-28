package com.corporealshift.footprints.ui.timelines

import android.content.Context
import com.corporealshift.footprints.api.HomeTimeline
import com.corporealshift.friendica.models.Item
import org.chromium.net.CronetEngine
import java.util.concurrent.Executor

class HomeTimelineModel(private val hostname: String): TimelineModel() {
    override suspend fun networkItems(
        context: Context,
        engine: CronetEngine,
        executor: Executor
    ): ArrayList<Item> {
        return HomeTimeline(engine, executor, context, hostname).getItems()
    }
}