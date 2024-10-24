package com.example.musicplayer.player

import android.app.Application
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.flow.MutableStateFlow

class AudioPlayer(private val application: Application) {
    private var player: ExoPlayer? = null
    private var playWhenReady: Boolean = false
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
        playWhenReady = true
        player?.playWhenReady = playWhenReady
    }

    fun pauseAudio() {
        playWhenReady = false
        player?.playWhenReady = playWhenReady
    }

    fun releasePlayer() {
        player?.let { player ->
            playbackPosition = player.currentPosition
            mediaItemIndex = player.currentMediaItemIndex
            playWhenReady = player.playWhenReady
            player.release()
        }
        player = null
    }

    fun skipToNext() {
        player?.seekToNextMediaItem()
    }

    fun skipToPrevious() {
        player?.seekToPreviousMediaItem()
    }

    fun clearMediaItems() {
        player?.clearMediaItems()
    }

    fun isPlayerInitialized(): Boolean {
        return player != null
    }

    fun addListener(playerListener: PlayerListener) {
        player?.addListener(playerListener)
    }

}