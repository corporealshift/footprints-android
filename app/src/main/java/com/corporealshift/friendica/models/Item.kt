package com.corporealshift.friendica.models

import org.json.JSONArray
import org.json.JSONObject

enum class ActivityType {
    POSTED, RESHARE, LIKE, DISLIKE, COMMENTED
}

data class Item(
    val text: String,
    val id: String,
    val createdAt: String,
    val createdBy: User,
    val activityType: ActivityType,
    val originalCreator: User?,
    val attachments: ArrayList<Media>?,
)

fun itemFromJSON(json: JSONObject): Item {
    val item = Item(
        text = json.getString("text"),
        id = json.getString("id"),
        createdAt = json.getString("created_at"),
        createdBy = userFromJSON(json.getJSONObject("user")),
        activityType = activityTypeFromJSON(json),
        originalCreator = userFromJSON(json.getJSONObject("friendica_author")),
        attachments = mediaFromJSONArray(json.optJSONArray("attachments"))
    )
    return item
}

fun activityTypeFromJSON(json: JSONObject): ActivityType {
    val is_an_announcement = !json
        .getJSONObject("friendica_activities")
        .getJSONArray("announce")
        .isNull(0)
    if (is_an_announcement) {
        return ActivityType.RESHARE
    }
    return ActivityType.POSTED
}

fun itemsFromJSONArray(json: JSONArray): ArrayList<Item> {
    val items = ArrayList<Item>()
    (0 until json.length()).forEach { i ->
        items.add(itemFromJSON(json.getJSONObject(i)))
    }
    return items
}

fun mediaFromJSONArray(json: JSONArray?): ArrayList<Media> {
    val media = ArrayList<Media>()
    if (json != null) {
        (0 until json.length()).forEach { i ->
            val mediaItem = mediaFromJSON(json.getJSONObject(i))
            if (mediaItem != null) {
                media.add(mediaItem)
            }
        }
    }
    return media
}

fun mediaFromJSON(json: JSONObject): Media? {
    val mimetype = json.getString("mimetype")
    if (mimetype.contains("image")) {
        return Photo(
            json.getString("url"),
            json.getInt("size"),
            "",
            json.getString("mimetype")
        )
    }
    return null
}