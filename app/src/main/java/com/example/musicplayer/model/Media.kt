package com.example.musicplayer.model

import android.net.Uri

data class Media(
    val name: String,
    val uri: Uri,
    val length: Int
)