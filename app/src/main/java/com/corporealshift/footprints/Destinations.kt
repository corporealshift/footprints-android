package com.corporealshift.footprints

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Place
import androidx.compose.ui.graphics.vector.ImageVector

interface Destination {
    val icon: ImageVector
    val route: String
}

object HomeTimeline: Destination {
    override val icon = Icons.Filled.Place
    override val route = "home_timeline"
}

object CreatePost: Destination {
    override val icon = Icons.Filled.Create
    override val route = "create_post"
}