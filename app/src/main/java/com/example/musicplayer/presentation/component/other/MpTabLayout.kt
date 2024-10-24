package com.example.musicplayer.presentation.component.other

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musicplayer.presentation.component.text.SimpleTextBold
import com.example.musicplayer.ui.theme.MusicPlayerTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Preview
@Composable
private fun MpTabLayoutPreview() {
    MusicPlayerTheme {
        MpTabLayout(selectedTab = 0,
            tabsList = persistentListOf("All", "Playlist", "Folder", "Artist"),
            onTabClicked = { })
    }
}

@Composable
fun MpTabLayout(
    selectedTab: Int, tabsList: ImmutableList<String>, onTabClicked: (pos: Int) -> Unit
) {
    TabRow(selectedTabIndex = selectedTab, tabs = {
        tabsList.forEachIndexed { idx, tabName ->
            Tab(modifier = Modifier.padding(vertical = 8.dp),
                selected = idx == selectedTab,
                onClick = { onTabClicked(idx) }) {
                SimpleTextBold(text = tabName)
            }
        }
    })


}