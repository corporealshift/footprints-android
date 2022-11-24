package com.corporealshift.footprints.api

import android.content.Context
import android.util.Log
import com.corporealshift.footprints.models.Creds
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

abstract class FriendicaList(
    private val engine: CronetEngine,
    private val executor: Executor,
    private val context: Context) {

    abstract val path: String;

    suspend fun getItems(): ArrayList<Item> {
        return suspendCoroutine {
                cont ->
            InternalData().getCredentials(context)?.let {
                val creds = credsBase64(it)
                val request = engine.newUrlRequestBuilder(
                    "https://footprints.community/$path",
                    object : ReadToMemoryCallback() {
                        override fun onSucceeded(
                            request: UrlRequest,
                            info: UrlResponseInfo,
                            bodyBytes: ByteArray
                        ) {
                            Log.println(Log.WARN, "resp", String(bodyBytes))
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
                    }, executor
                )
                    .addHeader("authorization", "Basic $creds")
                request.build().start()
            }
        }
    }
}