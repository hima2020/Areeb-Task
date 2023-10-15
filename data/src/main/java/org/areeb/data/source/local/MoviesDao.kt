package org.areeb.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.areeb.domain.model.LocalMovieEntity

@Dao
interface MoviesDao {
    @Query("SELECT * FROM movies")
    suspend fun getAllMovies(): List<LocalMovieEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(movie: LocalMovieEntity)
}