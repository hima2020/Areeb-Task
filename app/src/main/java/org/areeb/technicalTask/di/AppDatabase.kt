package org.areeb.technicalTask.di

import androidx.room.Database
import androidx.room.RoomDatabase
import org.areeb.data.source.local.MoviesDao
import org.areeb.domain.model.LocalMovieEntity

@Database(entities = [LocalMovieEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}