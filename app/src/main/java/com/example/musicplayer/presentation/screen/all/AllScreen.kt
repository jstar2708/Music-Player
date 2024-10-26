package com.example.musicplayer.presentation.screen.all

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.musicplayer.model.Audio
import com.example.musicplayer.model.SerializableAudio
import com.example.musicplayer.presentation.component.card.AudioCard
import com.example.musicplayer.presentation.viewmodel.AllViewModel
import com.example.musicplayer.presentation.viewmodel.PlayerViewModel
import com.example.musicplayer.ui.theme.MusicPlayerTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

@Preview
@Composable
private fun AllScreenPreview() {
    MusicPlayerTheme {
        AllScreen(audioList = persistentListOf(
            Audio(
                id = "",
                title = "MAK",
                artist = "Sonu Nigam",
                dateAdded = 0,
                thumbnail = null,
                duration = 0L,
                relativePath = "",
                uri = Uri.parse(""),
            ), Audio(
                id = "0",
                title = "MAK",
                artist = "Sonu Nigam",
                dateAdded = 0,
                thumbnail = null,
                duration = 0L,
                relativePath = "",
                uri = Uri.parse("")
            ), Audio(
                id = "0",
                title = "MAK",
                artist = "Sonu Nigam",
                dateAdded = 0,
                thumbnail = null,
                duration = 0L,
                relativePath = "",
                uri = Uri.parse("")
            ), Audio(
                id = "0",
                title = "MAK",
                artist = "Sonu Nigam",
                dateAdded = 0,
                thumbnail = null,
                duration = 0L,
                relativePath = "",
                uri = Uri.parse("")
            )
        ), navigateToPlayerScreen = {})
    }
}

@Composable
fun AllScreenRoot(
    playerViewModel: PlayerViewModel, navigateToPlayerScreen: () -> Unit
) {
    val allViewModel = hiltViewModel<AllViewModel>()
    LaunchedEffect(key1 = true) {
        // Initialize Player and add listener
        if (!playerViewModel.isPlayerInitialized()) {
            playerViewModel.initializeAudioPlayer()
        }
        // Add media items to list
        allViewModel.setAudioListForScreen(playerViewModel.scanFiles(0))
    }
    AllScreen(audioList = allViewModel.audioListForScreen.collectAsState().value.toImmutableList(),
        navigateToPlayerScreen = { serializableAudio ->
            playerViewModel.updateCurrentSong(serializableAudio.id)
            navigateToPlayerScreen()
        })
}

@Composable
fun AllScreen(
    audioList: ImmutableList<Audio>, navigateToPlayerScreen: (SerializableAudio) -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn(Modifier.fillMaxSize()) {
            items(audioList.size) {
                AudioCard(audio = audioList[it], navigateToPlayerScreen)
            }
        }
    }
}