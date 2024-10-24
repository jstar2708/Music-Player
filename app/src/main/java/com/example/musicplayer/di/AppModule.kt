package com.example.musicplayer.di

import android.app.Application
import com.example.musicplayer.player.AudioPlayer
import com.example.musicplayer.player.AudioScanner
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesAudioPlayer(app: Application): AudioPlayer {
        return AudioPlayer(app)
    }

    @Provides
    @Singleton
    fun providesAudioScanner(app: Application): AudioScanner {
        return AudioScanner(app)
    }
}