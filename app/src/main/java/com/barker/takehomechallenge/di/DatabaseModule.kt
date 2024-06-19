package com.barker.takehomechallenge.di

import android.content.Context
import androidx.room.Room
import com.barker.takehomechallenge.db.RouteDao
import com.barker.takehomechallenge.db.RouteDatabase
import com.barker.takehomechallenge.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideNoteDao(database: RouteDatabase): RouteDao = database.routeDao()

    @Singleton
    @Provides
    fun provideNoteDatabase(@ApplicationContext context: Context): RouteDatabase =
        Room.databaseBuilder(
            context,
            RouteDatabase::class.java,
            Constants.DB_NAME
        ).fallbackToDestructiveMigration()
            .build()

}