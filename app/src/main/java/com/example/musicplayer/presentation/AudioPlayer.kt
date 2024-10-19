package com.example.musicplayer.presentation

import android.app.Application
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.cache

class AudioPlayer(private val application: Application) {
    private var player: ExoPlayer? = null
    private var playWhenReady : MutableStateFlow<Boolean> = MutableStateFlow(false)
    private var mediaItemIndex = 0
    private var playbackPosition = 0L

    fun initializePlayer() {
        player = ExoPlayer.Builder(application).build()
    }

    fun setMediaItems(mediaItems: List<MediaItem>) {
        player?.setMediaItems(mediaItems, mediaItemIndex, playbackPosition)
        player?.prepare()
    }

    fun playAudio() {
        playWhenReady.value = true
        player?.playWhenReady = playWhenReady.value
    }

    fun pauseAudio() {
        playWhenReady.value = false
        player?.playWhenReady = playWhenReady.value
    }

    fun releasePlayer() {
        player?.let { player ->
            playbackPosition = player.currentPosition
            mediaItemIndex = player.currentMediaItemIndex
            playWhenReady.value = player.playWhenReady
            player.release()
        }
        player = null
    }

    fun isPlayEnabled(): StateFlow<Boolean> {
        return playWhenReady
    }

    fun skipToNext() {
        player?.seekToNextMediaItem()
    }

    fun skipToPrevious() {
        player?.seekToPreviousMediaItem()
    }

}