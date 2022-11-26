package com.corporealshift.footprints.ui.timelines;

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corporealshift.friendica.models.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.chromium.net.CronetEngine
import java.util.concurrent.Executor

abstract class TimelineModel(): ViewModel() {
    abstract suspend fun networkItems(
        context: Context,
        engine: CronetEngine,
        executor: Executor): ArrayList<Item>

    var items by mutableStateOf(ArrayList<Item>())
        private set
    fun updateItems(newItems: ArrayList<Item>) {
        items = newItems
    }

    fun loadAllItems(context: Context, engine: CronetEngine, executor: Executor) {
        viewModelScope.launch(Dispatchers.IO) {
            val items = networkItems(context, engine, executor)
            updateItems(items)
        }
    }
}
