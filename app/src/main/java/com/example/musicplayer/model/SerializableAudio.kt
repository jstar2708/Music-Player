package com.example.musicplayer.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
data class SerializableAudio(
    val id: String,
    val title: String,
    val uriString: String,
    val duration: Long,
    val thumbnail: ByteArray?,
    val artist: String?,
    val dateAdded: Int?,
    val relativePath: String?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SerializableAudio

        if (id != other.id) return false
        if (title != other.title) return false
        if (uriString != other.uriString) return false
        if (duration != other.duration) return false
        if (thumbnail != null) {
            if (other.thumbnail == null) return false
            if (!thumbnail.contentEquals(other.thumbnail)) return false
        } else if (other.thumbnail != null) return false
        if (artist != other.artist) return false
        if (dateAdded != other.dateAdded) return false
        if (relativePath != other.relativePath) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + uriString.hashCode()
        result = 31 * result + duration.hashCode()
        result = 31 * result + (thumbnail?.contentHashCode() ?: 0)
        result = 31 * result + (artist?.hashCode() ?: 0)
        result = 31 * result + (dateAdded ?: 0)
        result = 31 * result + (relativePath?.hashCode() ?: 0)
        return result
    }
}