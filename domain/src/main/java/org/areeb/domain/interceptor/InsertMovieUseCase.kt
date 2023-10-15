package org.areeb.domain.interceptor

import org.areeb.domain.model.MovieDetails
import org.areeb.domain.repo.Repository
import org.areeb.domain.toMovieEntity
import javax.inject.Inject

class InsertMovieUseCase @Inject constructor(private val repo: Repository) {
    suspend operator fun invoke(movie: MovieDetails) {
        return repo.insertMovie(movie.toMovieEntity())
    }
}