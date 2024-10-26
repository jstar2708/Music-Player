package com.example.musicplayer.presentation.component.seekbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicplayer.R
import com.example.musicplayer.ui.theme.MusicPlayerTheme


@Preview(showBackground = true)
@Composable
private fun MpSeekBarPreview() {
    MusicPlayerTheme {
        MpSeekBar(
            progress = .5f,
            isPlayEnabled = true,
            onSkipNextClick = {},
            onPlayClick = {},
            onSkipPreviousClick = {},
            currentTime = "00:00",
            finalTime = "04:00"
        )
    }
}

@Composable
fun MpSeekBar(
    progress: Float,
    modifier: Modifier = Modifier,
    currentTime: String,
    finalTime: String,
    isPlayEnabled: Boolean,
    onPlayClick: () -> Unit,
    onSkipPreviousClick: () -> Unit,
    onSkipNextClick: () -> Unit
) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = currentTime, color = Color.Black, fontSize = 14.sp)
            Text(text = finalTime, color = Color.Black, fontSize = 14.sp)
        }
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp),
            progress = {
                progress
            },
            gapSize = 0.dp,
            strokeCap = StrokeCap.Round,
            color = Color.Black,
            trackColor = Color.LightGray,
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Absolute.SpaceAround
        ) {
            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .clickable(onClick = onSkipPreviousClick),
                painter = painterResource(id = R.drawable.baseline_skip_previous_24),
                contentDescription = "previous icon",
                tint = Color.Black
            )
            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .clickable(onClick = onPlayClick),
                painter = painterResource(id = if (isPlayEnabled) R.drawable.baseline_pause_24 else R.drawable.baseline_play_arrow_24),
                contentDescription = "play icon"
            )
            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .clickable(onClick = onSkipNextClick),
                painter = painterResource(id = R.drawable.baseline_skip_next_24),
                contentDescription = "next icon"
            )
        }
    }
}