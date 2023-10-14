package org.areeb.data.source.remote

import org.areeb.domain.model.MovieDetails
import org.areeb.domain.model.MoviesListApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
    @GET("discover/movie")
    suspend fun getMoviesList(
        @Query("page") page: Int,

        ): MoviesListApiResponse

    @GET("movie/{movieId}?language=en-US")
    suspend fun getMovieDetails(@Path("movieId") movieId: Int): MovieDetails


}