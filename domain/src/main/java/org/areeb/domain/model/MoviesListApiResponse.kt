package org.areeb.domain.model

data class MoviesListApiResponse(
    val page: Int,
    val results: List<MovieDetails>,
    val total_pages: Int,
    val total_results: Int
)

