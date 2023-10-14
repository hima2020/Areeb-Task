package org.areeb.domain.interceptor

import org.areeb.domain.model.MovieDetails
import org.areeb.domain.repo.Repository
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val repo: Repository) {
    suspend operator fun invoke(movieId: Int): MovieDetails {
        return repo.getMovieDetails(movieId)
    }
}