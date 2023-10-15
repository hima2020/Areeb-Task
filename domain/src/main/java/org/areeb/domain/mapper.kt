package org.areeb.domain

import org.areeb.domain.model.LocalMovieEntity
import org.areeb.domain.model.MovieDetails

fun MovieDetails.toMovieEntity(): LocalMovieEntity {
    return LocalMovieEntity(
        id = this.id,
        adult = this.adult,
        backdrop_path = this.backdrop_path,
        original_language = this.original_language,
        original_title = this.original_title,
        overview = this.overview,
        popularity = this.popularity,
        poster_path = this.poster_path,
        release_date = this.release_date,
        title = this.title,
        video = this.video,
        vote_average = this.vote_average,
        vote_count = this.vote_count
    )
}

fun LocalMovieEntity.toLocalMovieEntity(): MovieDetails {
    return MovieDetails(
        id = this.id,
        adult = this.adult,
        backdrop_path = this.backdrop_path,
        original_language = this.original_language,
        original_title = this.original_title,
        overview = this.overview,
        popularity = this.popularity,
        poster_path = this.poster_path,
        release_date = this.release_date,
        title = this.title,
        video = this.video,
        vote_average = this.vote_average,
        vote_count = this.vote_count
    )
}