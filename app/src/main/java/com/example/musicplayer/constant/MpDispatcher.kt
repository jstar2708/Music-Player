package com.example.musicplayer.constant

import kotlinx.coroutines.Dispatchers

object MpDispatcher {
    val main = Dispatchers.Main
    val io = Dispatchers.IO
    val default = Dispatchers.Default
}