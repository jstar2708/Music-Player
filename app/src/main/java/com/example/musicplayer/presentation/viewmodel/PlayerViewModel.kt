package com.example.musicplayer.presentation.viewmodel

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import com.example.musicplayer.presentation.AudioPlayer
import com.google.common.collect.ImmutableList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val audioPlayer: AudioPlayer
) : ViewModel() {

    var isPlaying by mutableStateOf(false)

    fun initializeAudioPlayer() {
        audioPlayer.initializePlayer()
    }

    fun setMediaItems(mediaItems: List<MediaItem>) {
        audioPlayer.setMediaItems(mediaItems);
    }
    fun playAudio() {
        audioPlayer.playAudio()
    }

    fun pauseAudio() {
        audioPlayer.pauseAudio()
    }

    fun releasePlayer() {
        audioPlayer.releasePlayer()
    }

    fun isPlayEnabled() : StateFlow<Boolean> {
        return audioPlayer.isPlayEnabled()
    }

    fun skipToNext() {
        audioPlayer.skipToNext()
    }

    fun skipToPrevious() {
        audioPlayer.skipToPrevious()
    }
}