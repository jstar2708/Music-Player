package com.example.musicplayer.presentation.component.appbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicplayer.ui.theme.MusicPlayerTheme

@Preview(showBackground = true)
@Composable
private fun MpTopAppBarPreview() {
    MusicPlayerTheme {
        MpTopAppBar(
            title = "Name",
            onIconClick = {}
        )
    }
}

@Composable
fun MpTopAppBar(
    title: String,
    onIconClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .size(50.dp),
        contentAlignment = Alignment.CenterStart
    ) {

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

        Icon(
            modifier = Modifier
                .size(40.dp)
                .padding(8.dp)
                .clickable(onClick = onIconClick),
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back button icon"
        )
    }
}