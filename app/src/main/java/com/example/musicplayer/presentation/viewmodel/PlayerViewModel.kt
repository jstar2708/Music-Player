package com.example.musicplayer.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import com.example.musicplayer.model.Audio
import com.example.musicplayer.player.AudioPlayer
import com.example.musicplayer.player.AudioScanner
import com.example.musicplayer.player.PlayerListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val audioScanner: AudioScanner,
    private var audioPlayer: AudioPlayer,
    private val playerListener: PlayerListener
) : ViewModel(), Player.Listener {
    private val _audioList = MutableStateFlow<List<Audio>>(emptyList())
    var audioList = _audioList.asStateFlow()

    var isPlaying = playerListener._isPlaying.asStateFlow()

    val currentAudio: StateFlow<Audio?> = playerListener._currentAudioId.asStateFlow().map { audioId ->
        _audioList.value.find { audio -> audio.id == audioId }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, null)


    fun scanFiles(range: Int): List<Audio> {
        _audioList.value = when (range) {
            0 -> audioScanner.scanAllAudioFilesFromExternalDirectory()
            else -> emptyList()
        }
        return _audioList.value
    }

    private fun getAudioListFromCurrentAudio(audioList: List<Audio>): List<Audio> {
        audioList.indexOf(currentAudio.value).also { index ->
            if (index != -1) {
                return _audioList.value.subList(index, _audioList.value.size)
            }
        }
        return emptyList()
    }

    private fun updateAudioList(audioList: List<Audio>) {
        _audioList.value = audioList
        setMediaItems()
    }

    fun initializeAudioPlayer() {
        audioPlayer.initializePlayer()
        audioPlayer.addListener(playerListener)
    }

    private fun setMediaItems() {
        audioPlayer.clearMediaItems()
        audioPlayer.setMediaItems(audioList.value.map { audio ->
            MediaItem.Builder().setMediaId(audio.id).setUri(audio.uri).build()
        })
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

    fun skipToNext() {
        audioPlayer.skipToNext()
    }

    fun skipToPrevious() {
        audioPlayer.skipToPrevious()
    }

    fun isPlayerInitialized(): Boolean {
        return audioPlayer.isPlayerInitialized()
    }

    fun updateCurrentSong(audioId: String) {
        playerListener.updateCurrentAudioId(audioId)
    }

    fun updateAudioListAfterCurrentSong(audioList: List<Audio>) {
        updateAudioList(getAudioListFromCurrentAudio(audioList))
    }

}