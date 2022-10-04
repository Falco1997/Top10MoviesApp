package com.example.top10movies.repo

import com.example.top10movies.api.RetrofitInstance

class MoviesRepo {

    suspend fun getPopularMovies(apiKey: String) =
        RetrofitInstance.movieApi.getPopularMovies(apiKey)

    suspend fun getMovieDetailsById(apiKey: String, movieId: String) =
        RetrofitInstance.movieApi.getMovieDetailsById(movieId, apiKey)

}