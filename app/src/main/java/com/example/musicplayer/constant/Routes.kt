package com.example.musicplayer.constant

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object HomeScreen : Screen()

    @Serializable
    data object PlayerScreen : Screen()
}

@Serializable
sealed class Graph {
    @Serializable
    data object MainGraph : Graph()
}

