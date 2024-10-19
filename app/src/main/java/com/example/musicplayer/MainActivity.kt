package com.example.musicplayer

import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.MetadataRetriever
import com.example.musicplayer.presentation.screen.PlayerScreen
import com.example.musicplayer.presentation.viewmodel.PlayerViewModel
import com.example.musicplayer.ui.theme.MusicPlayerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val playerViewModel by viewModels<PlayerViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        playerViewModel.initializeAudioPlayer()
        val list = listOf(
            MediaItem.fromUri(Uri.parse("android.resource://$packageName/raw/millionaire")),
            MediaItem.fromUri(Uri.parse("android.resource://$packageName/raw/audio")),
            MediaItem.fromUri(Uri.parse("android.resource://$packageName/raw/audio")),
            MediaItem.fromUri(Uri.parse("android.resource://$packageName/raw/audio"))
        )
        playerViewModel.setMediaItems(list)
        val audioTitle = list[0].mediaMetadata.title ?: list[0].mediaMetadata.displayTitle
        ?: list[0].localConfiguration?.uri?.lastPathSegment
        val audioArtist = list[0].mediaMetadata.artist ?: "No artist"
        val retriever = MediaMetadataRetriever()
        retriever.setDataSource(applicationContext, list[0].localConfiguration?.uri)
        val thumbnail = retriever.embeddedPicture?.let {
            BitmapFactory.decodeByteArray(it, 0, it.size)
        }
        retriever.release()

        setContent {
            MusicPlayerTheme {
                val isPlaying = playerViewModel.isPlayEnabled().collectAsState().value
                if (thumbnail != null) {
                    PlayerScreen(
                        isPlayEnabled = isPlaying,
                        audioTitle = audioTitle.toString(),
                        audioArtist = audioArtist.toString(),
                        thumbnailUri = thumbnail,
                        onPlayClick = if (isPlaying) playerViewModel::pauseAudio else playerViewModel::playAudio,
                        onSkipPreviousClick = playerViewModel::skipToPrevious,
                        onSkipNextClick = playerViewModel::skipToNext
                    )
                }
            }
        }
    }
}

