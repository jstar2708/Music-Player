package com.example.musicplayer.presentation.utility

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.example.musicplayer.model.Audio
import com.example.musicplayer.model.SerializableAudio
import java.io.ByteArrayOutputStream


object Utility {
    fun Audio.toSerializableAudio(): SerializableAudio {
        val stream = ByteArrayOutputStream()
        this.thumbnail?.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val byteArray = stream.toByteArray()

        return SerializableAudio(
            id = this.id,
            title = this.title,
            uriString = this.uri.toString(),
            duration = this.duration,
            thumbnail = byteArray,
            artist = this.artist,
            dateAdded = this.dateAdded,
            relativePath = this.relativePath
        )
    }

    fun SerializableAudio.toAudio(): Audio {
        return Audio(
            id = this.id,
            title = this.title,
            uri = Uri.parse(this.uriString),
            duration = this.duration,
            thumbnail = BitmapFactory.decodeByteArray(this.thumbnail, 0, thumbnail?.size ?: 0),
            artist = this.artist,
            dateAdded = this.dateAdded,
            relativePath = this.relativePath
        )
    }
}