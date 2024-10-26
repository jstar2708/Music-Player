package com.example.musicplayer.player

import android.media.session.PlaybackState
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.Timeline
import kotlinx.coroutines.flow.MutableStateFlow

class PlayerListener : Player.Listener {
    val currentAudioId = MutableStateFlow<String?>(null)
    val isPlaying: MutableStateFlow<Boolean> = MutableStateFlow(false)

    override fun onIsPlayingChanged(isPlaying: Boolean) {
        super.onIsPlayingChanged(isPlaying)
        this.isPlaying.value = isPlaying
    }

    override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
        super.onMediaItemTransition(mediaItem, reason)
        if (mediaItem == null) {
            // Playlist ends
            isPlaying.value = false
        }
        currentAudioId.value = mediaItem?.mediaId
    }

    fun updateCurrentAudioId(audioId: String) {
        currentAudioId.value = audioId
    }

}