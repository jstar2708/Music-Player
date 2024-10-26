package com.example.musicplayer.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.musicplayer.model.Audio
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AllViewModel @Inject constructor() : ViewModel() {

    private val _audioListForScreen: MutableStateFlow<List<Audio>> = MutableStateFlow(emptyList())
    var audioListForScreen = _audioListForScreen.asStateFlow()

    fun setAudioListForScreen(audioList: List<Audio>) {
        _audioListForScreen.value = audioList
    }


}