package com.example.newrunningapp.di

import android.content.Context
import androidx.room.Room
import com.example.newrunningapp.db.RunningDatabase
import com.example.newrunningapp.other.Constants.RUNNING_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRunningdatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        RunningDatabase::class.java,
        RUNNING_DATABASE_NAME

    ).build()

    @Singleton
    @Provides

    fun providerRunDao(db: RunningDatabase) = db.getRunDao()
}