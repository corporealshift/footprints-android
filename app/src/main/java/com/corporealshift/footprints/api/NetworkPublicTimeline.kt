package com.corporealshift.footprints.api

import android.content.Context
import org.chromium.net.CronetEngine
import java.util.concurrent.Executor

class NetworkPublicTimeline(
    engine: CronetEngine,
    executor: Executor,
    context: Context,
    hostname: String,
): FriendicaList(engine, executor, context, hostname) {
    override val path: String = "api/statuses/networkpublic_timeline"
}