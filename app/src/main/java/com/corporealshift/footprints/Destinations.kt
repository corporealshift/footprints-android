package com.corporealshift.footprints

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.ui.graphics.vector.ImageVector

interface Destination {
    val icon: ImageVector
    val route: String
}

object GlobalNetwork: Destination {
    override val icon = Icons.Filled.Place
    override val route = "global"
}