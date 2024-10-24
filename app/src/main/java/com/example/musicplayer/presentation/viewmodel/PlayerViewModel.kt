package com.example.musicplayer.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import com.example.musicplayer.constant.MpDispatcher
import com.example.musicplayer.model.Audio
import com.example.musicplayer.player.AudioPlayer
import com.example.musicplayer.player.AudioScanner
import com.example.musicplayer.player.PlayerListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
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

    private val _currentAudio: MutableStateFlow<Audio?> = MutableStateFlow(null)
    var currentAudio = _currentAudio.asStateFlow()

    fun scanFiles(range: Int) {
        _audioList.value = when (range) {
            0 -> audioScanner.scanAllAudioFilesFromExternalDirectory()
            else -> emptyList()
        }
    }

    private fun getAudioId() = viewModelScope.launch(MpDispatcher.default) {
        playerListener.currentAudioId.collectLatest { audioId ->
            audioId?.let {
                getAudioFromAudioId(it)
            }
        }
    }

    private fun getAudioFromAudioId(audioId: String) {
        _currentAudio.value = _audioList.value.find { audio -> audio.id == audioId }
    }

    private fun getAudioListFromCurrentAudio(): List<Audio> {
        _audioList.value.indexOf(_currentAudio.value).also { index ->
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
        getAudioId()
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

    fun updateAudioListAfterCurrentSong() {
        updateAudioList(getAudioListFromCurrentAudio())
    }

}