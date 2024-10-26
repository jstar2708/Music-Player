package com.example.musicplayer.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.musicplayer.model.SerializableAudio
import com.example.musicplayer.presentation.component.appbar.MpTopAppBar
import com.example.musicplayer.presentation.component.other.MpTabLayout
import com.example.musicplayer.presentation.screen.all.AllScreenRoot
import com.example.musicplayer.presentation.screen.artist.ArtistScreenRoot
import com.example.musicplayer.presentation.screen.folder.FolderScreenRoot
import com.example.musicplayer.presentation.screen.playlist.PlaylistScreenRoot
import com.example.musicplayer.presentation.viewmodel.HomeViewModel
import com.example.musicplayer.presentation.viewmodel.PlayerViewModel
import com.example.musicplayer.ui.theme.MusicPlayerTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf


@Preview
@Composable
private fun HomeScreenPreview() {
    MusicPlayerTheme {
        HomeScreen(selectedTab = 0,
            tabList = persistentListOf("All", "Playlist", "Folder", "Artist"),
            onTabClicked = {},
            tabScreens = {},
            backPress = {})
    }
}

@Composable
fun HomeScreenRoot(
    playerViewModel: PlayerViewModel,
    navigateToPlayerScreen: () -> Unit,
    backPress: () -> Unit
) {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    HomeScreen(selectedTab = homeViewModel.selectedTab,
        tabList = homeViewModel.tabList,
        onTabClicked = homeViewModel::onTabClicked,
        backPress = backPress,
        tabScreens = {
            when (homeViewModel.selectedTab) {
                0 -> AllScreenRoot(playerViewModel, navigateToPlayerScreen)
                1 -> PlaylistScreenRoot()
                2 -> FolderScreenRoot()
                else -> ArtistScreenRoot()
            }
        })
}

@Composable
fun HomeScreen(
    selectedTab: Int,
    tabList: ImmutableList<String>,
    onTabClicked: (Int) -> Unit,
    tabScreens: @Composable () -> Unit,
    backPress: () -> Unit
) {
    Scaffold(topBar = {
        MpTopAppBar(
            title = "Music Player", icon = Icons.Filled.Menu, onIconClick = backPress
        )
    }) {
        Column(modifier = Modifier.padding(it)) {
            MpTabLayout(
                selectedTab = selectedTab, tabsList = tabList, onTabClicked = onTabClicked
            )
            tabScreens()
        }
    }
}