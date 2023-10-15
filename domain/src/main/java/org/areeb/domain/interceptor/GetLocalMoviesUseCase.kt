package org.areeb.domain.interceptor

import org.areeb.domain.model.MovieDetails
import org.areeb.domain.repo.Repository
import javax.inject.Inject

class GetLocalMoviesUseCase @Inject constructor(private val repo: Repository) {
    suspend operator fun invoke(): List<MovieDetails> {
        return repo.getLocalMovies()
    }
}