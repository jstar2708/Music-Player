package com.example.musicplayer.presentation.component.appbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musicplayer.presentation.component.text.MediumTextBold
import com.example.musicplayer.ui.theme.MusicPlayerTheme

@Preview(showBackground = true)
@Composable
private fun MpTopAppBarPreview() {
    MusicPlayerTheme {
        MpTopAppBar(title = "Name", icon = Icons.AutoMirrored.Filled.ArrowBack, onIconClick = {})
    }
}

@Composable
fun MpTopAppBar(
    title: String,
    icon: ImageVector,
    onIconClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .size(50.dp), contentAlignment = Alignment.CenterStart
    ) {

        MediumTextBold(
            modifier = Modifier.fillMaxWidth(), text = title, alignment = TextAlign.Center
        )

        Icon(
            modifier = Modifier
                .size(40.dp)
                .padding(8.dp)
                .clickable(onClick = onIconClick),
            imageVector = icon,
            contentDescription = "Back button icon"
        )
    }
}