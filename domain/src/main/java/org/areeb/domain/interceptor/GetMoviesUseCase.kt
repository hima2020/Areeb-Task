package org.areeb.domain.interceptor

import org.areeb.domain.model.MoviesListApiResponse
import org.areeb.domain.repo.Repository
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repo: Repository) {
    suspend operator fun invoke(page:Int): MoviesListApiResponse {
        return repo.getMoviesList(page)
    }
}