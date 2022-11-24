package com.corporealshift.footprints.api

import android.content.Context
import org.chromium.net.CronetEngine
import java.util.concurrent.Executor

class HomeTimeline(
    engine: CronetEngine,
    executor: Executor,
    context: Context
): FriendicaList(engine, executor, context) {
    override val path: String = "api/statuses/home_timeline"
}