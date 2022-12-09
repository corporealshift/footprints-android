package com.corporealshift.footprints.ui.menubar

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Menu(
    title: String,
    showCreate: Boolean,
    showBack: Boolean,
    modifier: Modifier = Modifier,
    onCreatePost: () -> Unit,
    onBack: () -> Unit,
) {
    TopAppBar(
        backgroundColor = Color.White,
        contentColor = Color.Black,
        title = { Text(text = titleizeHostname(title)) },
        navigationIcon = {
            if (showBack) {
                IconButton(onClick = onBack) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        modifier = modifier.size(30.dp)
                    )
                }
            } else {
                IconButton(onClick = {}) {
                    Icon(
                        Icons.Filled.Menu,
                        contentDescription = "Menu",
                        modifier = modifier.size(30.dp)
                    )
                }
            }
        },
        actions = {
            if (showCreate) {
                IconButton(onClick = onCreatePost) {
                    Icon(
                        Icons.Filled.AddCircle,
                        contentDescription = "Create Post",
                        modifier = modifier.size(30.dp)
                    )
                }
            }
        }
    )
}

fun titleizeHostname(hostname: String): String {
    // Remove the top level domain if it is com, org, etc and capitalize the rest nicely.
    // Keep subdomains.
    val loweredHostname = hostname.lowercase()
    var domainParts = loweredHostname.split(".")
    // Remove co.uk and similar
    if (domainParts.size >= 3 && domainParts[domainParts.size - 2] == "co") {
        domainParts = domainParts.subList(0, domainParts.size - 2)
    }
    // Remove com, org, io
    if (isBoringTLD(domainParts.last())) {
        domainParts = domainParts.subList(0, domainParts.size - 1)
    }
    // If we have a more unique domain just uh...leave it alone
    if (domainParts.size > 3) {
        return hostname
    }

    return domainParts
        .joinToString(" ")
        .replaceFirstChar { it.uppercase() }
}

fun isBoringTLD(tld: String): Boolean {
    val boringOnes = listOf("com", "org", "net", "gov", "biz", "edu", "io", "cc")

    return boringOnes.contains(tld)
}