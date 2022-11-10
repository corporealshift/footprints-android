package com.corporealshift.friendica.models

import org.json.JSONObject

data class User(
    val id: String,
    val name: String,
    val url: String,
    val description: String,
    val profileImageURL: String,
)

fun userFromJSON(json: JSONObject): User {
    return User(
        json.getString("id"),
        json.getString("name"),
        json.getString("url"),
        json.getString("description"),
        json.getString("profile_image_url"),
    )
}
