package com.example.musicplayer.presentation.component.card

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.musicplayer.R
import com.example.musicplayer.model.Audio
import com.example.musicplayer.model.SerializableAudio
import com.example.musicplayer.presentation.component.text.SimpleText
import com.example.musicplayer.presentation.component.text.SmallText
import com.example.musicplayer.presentation.utility.Utility.toSerializableAudio
import com.example.musicplayer.ui.theme.MusicPlayerTheme

@Preview
@Composable
private fun AudioCardPreview() {
    MusicPlayerTheme {
        AudioCard(audio = Audio(
            id = "",
            title = "MAK",
            artist = "Sonu Nigam",
            dateAdded = 0,
            thumbnail = null,
            duration = 0L,
            relativePath = "",
            uri = Uri.parse("")
        ), onCardClick = {})
    }
}

@Composable
fun AudioCard(audio: Audio, onCardClick: (SerializableAudio) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(4.dp)
            .clickable {
                onCardClick(audio.toSerializableAudio())
            }, verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier.size(50.dp),
            model = audio.thumbnail,
            error = painterResource(id = R.drawable.default_audio_thumbnail),
            contentDescription = "audio thumbnail"
        )
        Spacer(modifier = Modifier.width(4.dp))
        Column(modifier = Modifier.weight(1f)) {
            SimpleText(modifier = Modifier.fillMaxWidth(), text = audio.title)
            SmallText(modifier = Modifier.fillMaxWidth(), text = audio.artist ?: "")
        }

        Icon(imageVector = Icons.Outlined.MoreVert, contentDescription = "options icon")

    }
}