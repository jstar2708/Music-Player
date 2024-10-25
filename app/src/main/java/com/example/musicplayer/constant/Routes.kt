package com.example.musicplayer.constant

import com.example.musicplayer.model.SerializableAudio
import kotlinx.serialization.Serializable

@Serializable
object HomeScreen

@Serializable
data class PlayerScreen(val audio: SerializableAudio)

@Serializable
object MainGraph