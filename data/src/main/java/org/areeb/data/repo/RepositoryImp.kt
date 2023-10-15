package org.areeb.data.repo

import org.areeb.data.source.local.MoviesDao
import org.areeb.data.source.remote.ApiService
import org.areeb.domain.model.LocalMovieEntity
import org.areeb.domain.model.MovieDetails
import org.areeb.domain.model.MoviesListApiResponse
import org.areeb.domain.repo.Repository
import org.areeb.domain.toLocalMovieEntity
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val apiService: ApiService,
    private val moviesDao: MoviesDao
) :
    Repository {
    override suspend fun getMoviesList(page: Int): MoviesListApiResponse {
        return apiService.getMoviesList(page)
    }

    override suspend fun getLocalMovies(): List<MovieDetails> {
        return moviesDao.getAllMovies().map { it.toLocalMovieEntity() }
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return apiService.getMovieDetails(movieId)
    }

    override suspend fun insertMovie(movieEntity: LocalMovieEntity) {
        moviesDao.insertMovie(movieEntity)
    }


}

