package com.example.top10movies.api

import com.example.top10movies.api.data.MovieDetails
import com.example.top10movies.api.data.MoviesResult
import com.example.top10movies.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    @GET("movie/popular/")
    suspend fun getPopularMovies(
        @Query("api_key")
        apiKey: String = API_KEY
    ): Response<MoviesResult>

    @GET("movie/{movieId}")
    suspend fun getMovieDetailsById(
        @Path("movieId") movieId: String,
        @Query("api_key") apiKey: String = API_KEY
    ): Response<MovieDetails>

}