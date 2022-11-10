package com.corporealshift.friendica.models

import org.json.JSONArray
import org.json.JSONObject
import java.time.LocalDateTime

data class Item(
    val text: String,
    val id: String,
    val createdAt: String,
    val createdBy: User,
)

fun itemFromJSON(json: JSONObject): Item {
    return Item(
        text = json.getString("text"),
        id = json.getString("id"),
        createdAt = json.getString("created_at"),
        createdBy = userFromJSON(json.getJSONObject("user")),
    )
}

fun itemsFromJSONArray(json: JSONArray): ArrayList<Item> {
    val items = ArrayList<Item>()
    (0 until json.length()).forEach { i ->
        items.add(itemFromJSON(json.getJSONObject(i)))
    }
    return items
}