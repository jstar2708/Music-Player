package com.example.musicplayer.player

import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import kotlinx.coroutines.flow.MutableStateFlow

class PlayerListener : Player.Listener {
    val _currentAudioId = MutableStateFlow<String?>(null)
    val _isPlaying: MutableStateFlow<Boolean> = MutableStateFlow(false)

    override fun onIsPlayingChanged(isPlaying: Boolean) {
        super.onIsPlayingChanged(isPlaying)
        _isPlaying.value = isPlaying
    }

    override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
        super.onMediaItemTransition(mediaItem, reason)
        if (mediaItem == null) {
            // Playlist ends
            _isPlaying.value = false
        }
        _currentAudioId.value = mediaItem?.mediaId
    }

    fun updateCurrentAudioId(audioId: String) {
        _currentAudioId.value = audioId
    }

}