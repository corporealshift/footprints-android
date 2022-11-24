package com.corporealshift.footprints.api

import android.content.Context
import org.chromium.net.CronetEngine
import java.util.concurrent.Executor

class NetworkPublicTimeline(
    engine: CronetEngine,
    executor: Executor,
    context: Context
): FriendicaList(engine, executor, context) {
    override val path: String = "api/statuses/networkpublic_timeline"
}