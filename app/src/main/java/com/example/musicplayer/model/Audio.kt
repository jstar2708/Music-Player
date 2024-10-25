package com.example.musicplayer.model

import android.graphics.Bitmap
import android.net.Uri

data class Audio(
    val id: String,
    val title: String,
    val uri: Uri,
    val duration: Long,
    val thumbnail: Bitmap?,
    val artist: String?,
    val dateAdded: Int?,
    val relativePath: String?
)