package com.corporealshift.footprints.api

import android.content.Context
import org.chromium.net.CronetEngine
import java.util.concurrent.Executor

class HomeTimeline(
    engine: CronetEngine,
    executor: Executor,
    context: Context,
    hostname: String
): FriendicaList(engine, executor, context, hostname) {
    override val path: String = "api/statuses/home_timeline"
}