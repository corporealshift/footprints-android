package com.corporealshift.footprints.models

import java.util.*

data class Creds(
    val username: String,
    val password: String,
)

fun credsBase64(creds: Creds): String {
    return Base64.getEncoder().encodeToString("${creds.username}:${creds.password}".toByteArray())
}