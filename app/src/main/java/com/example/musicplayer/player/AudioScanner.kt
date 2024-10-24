package com.example.musicplayer.player

import android.content.ContentUris
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.provider.MediaStore
import com.example.musicplayer.model.Audio

class AudioScanner(
    private val appContext: Context
) {
    fun scanAllAudioFilesFromExternalDirectory(): List<Audio> {
        val mediaItems = ArrayList<Audio>()
        val projections = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.DATE_ADDED,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.RELATIVE_PATH
        )
        appContext.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projections, null, null
        )?.use { cursor ->
            val id = cursor.getColumnIndex(MediaStore.Audio.Media._ID)
            val artist = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)
            val title = cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME)
            val dateAdded = cursor.getColumnIndex(MediaStore.Audio.Media.DATE_ADDED)
            val duration = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)
            val relativePath = cursor.getColumnIndex(MediaStore.Audio.Media.RELATIVE_PATH)
            while (cursor.moveToNext()) {
                val uri = ContentUris.withAppendedId(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, cursor.getLong(id)
                )

                mediaItems.add(
                    Audio(
                        id = id.toString(),
                        title = cursor.getString(title),
                        artist = cursor.getString(artist),
                        uri = ContentUris.withAppendedId(
                            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, cursor.getLong(id)
                        ),
                        duration = cursor.getLong(duration),
                        thumbnail = retrieveThumbnail(uri),
                        dateAdded = cursor.getInt(dateAdded),
                        relativePath = cursor.getString(relativePath)
                    )
                )
            }
        }
        return mediaItems
    }

    fun extractAudioFromUri(uriString: String) {
        val uri = Uri.parse(uriString)
        val mediaMetadataRetriever = MediaMetadataRetriever()
        mediaMetadataRetriever.setDataSource(appContext, uri)


    }

    private fun retrieveThumbnail(uri: Uri): Bitmap? {
        val mediaMetadataRetriever = MediaMetadataRetriever()
        mediaMetadataRetriever.setDataSource(appContext, uri)
        return mediaMetadataRetriever.embeddedPicture?.size?.let {
            BitmapFactory.decodeByteArray(
                mediaMetadataRetriever.embeddedPicture, 0, it
            )
        }
    }
}