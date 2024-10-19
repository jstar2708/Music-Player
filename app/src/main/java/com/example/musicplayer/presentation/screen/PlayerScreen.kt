package com.example.musicplayer.presentation.screen

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.musicplayer.R
import com.example.musicplayer.presentation.component.appbar.MpTopAppBar
import com.example.musicplayer.presentation.component.seekbar.MpSeekBar
import com.example.musicplayer.ui.theme.MusicPlayerTheme

@Preview(showBackground = true)
@Composable
private fun PlayerScreenPreview() {
    MusicPlayerTheme {
        PlayerScreen(
            isPlayEnabled = true,
            audioArtist = "",
            audioTitle = "",
            thumbnailUri = Uri.parse(""),
            onPlayClick = {  },
            onSkipPreviousClick = {  }) {

        }
    }
}

@Composable
fun PlayerScreen(
    isPlayEnabled: Boolean,
    audioTitle: String,
    audioArtist: String,
    thumbnailUri: Any,
    onPlayClick: () -> Unit,
    onSkipPreviousClick: () -> Unit,
    onSkipNextClick: () -> Unit
) {
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MpTopAppBar(audioTitle, {})
            AsyncImage(
                modifier = Modifier
                    .size(300.dp)
                    .weight(.9f),
                model = thumbnailUri as Bitmap,
                contentDescription = "Music image"
            )
            MpSeekBar(
                modifier = Modifier.weight(.15f),
                isPlayEnabled = isPlayEnabled,
                onSkipPreviousClick = onSkipPreviousClick,
                onPlayClick = onPlayClick,
                onSkipNextClick = onSkipNextClick
            )
        }
    }
}