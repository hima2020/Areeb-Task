package org.areeb.data.repo

import org.areeb.data.source.remote.ApiService
import org.areeb.domain.model.MovieDetails
import org.areeb.domain.model.MoviesListApiResponse
import org.areeb.domain.repo.Repository
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val apiService: ApiService) :
    Repository {
    override suspend fun getMoviesList(page: Int): MoviesListApiResponse {
        return apiService.getMoviesList(page)
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return apiService.getMovieDetails(movieId)
    }


}