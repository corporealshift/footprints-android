package com.corporealshift.footprints.ui.posts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.corporealshift.footprints.ui.user.Author
import com.corporealshift.footprints.ui.user.ReshareAuthor
import com.corporealshift.friendica.models.Item

@Composable
fun ResharedPost(item: Item, modifier: Modifier = Modifier) {
    Card(
        elevation = 5.dp,
        modifier = modifier.fillMaxWidth()
            .background(color = Color(255, 255, 255, ))
    ) {
        Column(modifier = modifier.padding(start = 10.dp, end = 10.dp, top = 5.dp)) {
            Row() {
                ReshareAuthor(
                    author = item.createdBy,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }
            if (item.originalCreator != null) {
                Author(
                    modifier = Modifier.padding(end = 5.dp),
                    avatarURL = item.originalCreator.profileImageURL,
                    name = item.originalCreator.name
                )
            }
            Text(
                text = item.text,
                Modifier.padding(5.dp)
            )
        }
    }
}