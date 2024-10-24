package com.example.musicplayer.presentation.component.text

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicplayer.ui.theme.MusicPlayerTheme

@Preview(showBackground = true)
@Composable
private fun TextPreview() {
    MusicPlayerTheme {
        Column {
            SmallText(text = "This is a small text")
            SmallTextBold(text = "This is a small bold text")
            SimpleText(
                text = "This is a simple text."
            )
            HorizontalDivider(thickness = 2.dp)
            SimpleTextBold(text = "This is a simple bold text.")
            HorizontalDivider(thickness = 2.dp)
            MediumText(
                text = "This is a medium text."
            )
            HorizontalDivider(thickness = 2.dp)
            MediumTextBold(text = "This is a medium bold text.")
            HorizontalDivider(thickness = 2.dp)
            HeaderText(text = "This is a header text.")
            HorizontalDivider(thickness = 2.dp)
            HeaderTextBold(text = "This is a header bold text.")
        }
    }
}

@Composable
fun SmallText(
    modifier: Modifier = Modifier, text: String, alignment: TextAlign = TextAlign.Start
) {
    Text(modifier = modifier, text = text, textAlign = alignment, fontSize = 14.sp)
}

@Composable
fun SmallTextBold(
    modifier: Modifier = Modifier, text: String, alignment: TextAlign = TextAlign.Start
) {
    Text(modifier = modifier, text = text, textAlign = alignment, fontSize = 14.sp, fontWeight = FontWeight.Bold)
}

@Composable
fun SimpleText(
    modifier: Modifier = Modifier, text: String, alignment: TextAlign = TextAlign.Start
) {
    Text(
        modifier = modifier, text = text, textAlign = alignment, fontSize = 18.sp
    )
}

@Composable
fun SimpleTextBold(
    modifier: Modifier = Modifier, text: String, alignment: TextAlign = TextAlign.Start
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = alignment,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun MediumText(
    modifier: Modifier = Modifier, text: String, alignment: TextAlign = TextAlign.Start
) {
    Text(
        modifier = modifier, text = text, textAlign = alignment, fontSize = 22.sp
    )
}

@Composable
fun MediumTextBold(
    modifier: Modifier = Modifier, text: String, alignment: TextAlign = TextAlign.Start
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = alignment,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun HeaderText(
    modifier: Modifier = Modifier, text: String, alignment: TextAlign = TextAlign.Start
) {
    Text(text = text, modifier = modifier, textAlign = alignment, fontSize = 26.sp)
}

@Composable
fun HeaderTextBold(
    modifier: Modifier = Modifier, text: String, alignment: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        modifier = modifier,
        textAlign = alignment,
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold
    )
}