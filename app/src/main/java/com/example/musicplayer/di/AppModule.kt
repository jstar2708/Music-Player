package com.example.musicplayer.di

import android.app.Application
import com.example.musicplayer.presentation.AudioPlayer
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
}