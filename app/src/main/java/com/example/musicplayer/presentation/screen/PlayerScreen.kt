package com.example.musicplayer.presentation.screen

import android.content.res.Resources
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import coil3.compose.AsyncImage
import com.example.musicplayer.R
import com.example.musicplayer.model.Audio
import com.example.musicplayer.presentation.component.appbar.MpTopAppBar
import com.example.musicplayer.presentation.component.seekbar.MpSeekBar
import com.example.musicplayer.presentation.component.text.MediumTextBold
import com.example.musicplayer.presentation.viewmodel.PlayerViewModel
import com.example.musicplayer.ui.theme.MusicPlayerTheme

@Preview(showBackground = true)
@Composable
private fun PlayerScreenPreview() {
    MusicPlayerTheme {
        PlayerScreen(
            isPlayEnabled = true,
            audio = Audio(
                id = "",
                artist = "Jaideep Kumar Singh",
                title = "Main hoon na",
                thumbnail = LocalContext.current.resources.getDrawable(
                    R.drawable.default_audio_thumbnail, Resources.getSystem().newTheme()
                ).toBitmap(width = 1000, height = 1000),
                duration = 0,
                dateAdded = 0,
                relativePath = "",
                uri = Uri.parse("")
            ),
            onPlayClick = { },
            onSkipPreviousClick = { },
            progress = 0f,
            currentTime = "00:00",
            finalTime = "04:00"
        ) {

        }
    }
}

@Composable
fun PlayerScreenRoot(playerViewModel: PlayerViewModel) {
    val isPlaying = playerViewModel.isPlaying.collectAsState().value
    val duration by remember {
        mutableStateOf(playerViewModel.getAudioDuration())
    }
    PlayerScreen(
        isPlayEnabled = isPlaying,
        progress = playerViewModel.progress.collectAsState().value,
        audio = playerViewModel.currentAudio.collectAsState().value,
        onPlayClick = if (isPlaying) playerViewModel::pauseAudio else playerViewModel::playAudio,
        onSkipPreviousClick = playerViewModel::skipToPrevious,
        onSkipNextClick = playerViewModel::skipToNext,
        finalTime = duration,
        currentTime = playerViewModel.currentTime.collectAsState().value
    )
}

@Composable
fun PlayerScreen(
    isPlayEnabled: Boolean,
    currentTime: String,
    finalTime: String,
    progress: Float,
    audio: Audio?,
    onPlayClick: () -> Unit,
    onSkipPreviousClick: () -> Unit,
    onSkipNextClick: () -> Unit
) {
    Surface {
        Column(
            modifier = Modifier
                .background(Color.LightGray)
                .fillMaxSize()
                .padding(4.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MpTopAppBar(audio?.title ?: "Unplayable", Icons.AutoMirrored.Filled.ArrowBack, {})
            AsyncImage(
                modifier = Modifier
                    .size(300.dp)
                    .weight(.9f),
                model = audio?.thumbnail,
                error = painterResource(id = R.drawable.default_audio_thumbnail),
                contentDescription = "Music image"
            )
            MediumTextBold(
                text = audio?.artist ?: "No artist",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            MpSeekBar(
                modifier = Modifier.weight(.15f),
                currentTime = currentTime,
                isPlayEnabled = isPlayEnabled,
                onSkipPreviousClick = onSkipPreviousClick,
                onPlayClick = onPlayClick,
                onSkipNextClick = onSkipNextClick,
                progress = progress,
                finalTime = finalTime
            )
        }
    }
}