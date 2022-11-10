package com.corporealshift.footprints.api

import android.content.Context
import android.util.Log
import com.corporealshift.footprints.models.credsBase64
import com.corporealshift.footprints.prefs.InternalData
import com.corporealshift.footprints.requests.ReadToMemoryCallback
import com.corporealshift.friendica.models.Item
import com.corporealshift.friendica.models.itemsFromJSONArray
import org.chromium.net.CronetEngine
import org.chromium.net.CronetException
import org.chromium.net.UrlRequest
import org.chromium.net.UrlResponseInfo
import org.json.JSONArray
import org.json.JSONTokener
import java.util.concurrent.Executor
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class NetworkPublicTimeline(private val engine: CronetEngine, private val executor: Executor, private val context: Context) {

    suspend fun getItems(): ArrayList<Item> {
        val creds = credsBase64(InternalData().getCredentials(context))
        Log.println(Log.WARN, "Creds", creds)
        return suspendCoroutine {
                cont ->
            val request = engine.newUrlRequestBuilder(
                "https://footprints.community/api/statuses/networkpublic_timeline",
                object: ReadToMemoryCallback() {
                override fun onSucceeded(
                    request: UrlRequest,
                    info: UrlResponseInfo,
                    bodyBytes: ByteArray) {
                    Log.println(Log.WARN,"resp", String(bodyBytes))
                    cont.resume(itemsFromJSONArray(JSONTokener(String(bodyBytes)).nextValue() as JSONArray))
                }

                override fun onFailed(
                    request: UrlRequest,
                    info: UrlResponseInfo,
                    error: CronetException
                ) {
                    Log.w("API", "Cronet download failed!", error)
                    cont.resume(ArrayList<Item>())
                }
            }, executor)
                .addHeader("authorization", "Basic ${creds}")
            request.build().start()
        }
    }
}