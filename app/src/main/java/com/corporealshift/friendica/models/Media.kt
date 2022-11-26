package com.corporealshift.friendica.models

interface Media

data class Photo(
    val url: String,
    val size: Int,
    val caption: String,
    val mimetype: String,
): Media
