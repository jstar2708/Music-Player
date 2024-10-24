package com.example.musicplayer.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.musicplayer.player.AudioScanner
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val audioScanner: AudioScanner
) : ViewModel() {
    val tabList: ImmutableList<String> = persistentListOf("All", "PlayList", "Folder", "Artist")
    var selectedTab by mutableIntStateOf(0)

    fun onTabClicked(pos: Int) {
        selectedTab = pos
    }
}