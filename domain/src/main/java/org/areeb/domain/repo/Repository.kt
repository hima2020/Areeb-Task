package org.areeb.domain.repo

import org.areeb.domain.model.LocalMovieEntity
import org.areeb.domain.model.MovieDetails
import org.areeb.domain.model.MoviesListApiResponse


interface Repository {
    suspend fun getMoviesList(page: Int): MoviesListApiResponse
    suspend fun getLocalMovies(): List<MovieDetails>
    suspend fun getMovieDetails(movieId: Int): MovieDetails
    suspend fun insertMovie(movieEntity: LocalMovieEntity)

}