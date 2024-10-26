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
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val audioScanner: AudioScanner,
    private var audioPlayer: AudioPlayer,
    private val playerListener: PlayerListener
) : ViewModel(), Player.Listener {
    private val _currentTime: MutableStateFlow<String> = MutableStateFlow("00:00")
    var currentTime = _currentTime.asStateFlow()
    private val _progress: MutableStateFlow<Float> = MutableStateFlow(0.0f)
    var progress = _progress.asStateFlow()
    private val _audioList = MutableStateFlow<List<Audio>>(emptyList())
    var audioList = _audioList.asStateFlow()

    var isPlaying = playerListener.isPlaying.asStateFlow()

    val currentAudio: StateFlow<Audio?> =
        playerListener.currentAudioId.asStateFlow().map { audioId ->
            _audioList.value.find { audio -> audio.id == audioId }
        }.stateIn(viewModelScope, SharingStarted.Eagerly, null)


    fun scanFiles(range: Int): List<Audio> {
        val audioList = when (range) {
            0 -> audioScanner.scanAllAudioFilesFromExternalDirectory()
            else -> emptyList()
        }
        updateAudioList(audioList)
        return audioList
    }

    private fun initializeTimer() = viewModelScope.launch(MpDispatcher.default) {
        var counter = 0L
        val duration = currentAudio.value?.duration ?: 0L
        while (counter < duration) {
            if (isPlaying.value) {
                delay(100)
                counter += 100
                _progress.value = counter / duration.toFloat()
                getCurrentAudioDuration(counter)
            }
        }
    }

    private fun getCurrentAudioDuration(counter: Long) {
        val dateFormat = SimpleDateFormat("mm:ss", Locale.getDefault())
        _currentTime.value = dateFormat.format(Date(counter))
    }

    private fun getAudioIndexFromCurrentList(): Int {
        return _audioList.value.indexOf(currentAudio.value)
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
        val currentAudioIndex = getAudioIndexFromCurrentList()
        audioPlayer.setCurrentAudio(currentAudioIndex)
        initializeTimer()
    }

    fun getAudioDuration(): String {
        val duration = currentAudio.value?.duration ?: 0L
        val dateFormat = SimpleDateFormat("mm:ss", Locale.getDefault())
        return dateFormat.format(Date(duration))
    }


}