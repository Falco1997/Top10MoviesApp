package com.example.top10movies.repo

import com.example.top10movies.api.RetrofitInstance

class MoviesRepo {

    suspend fun getPopularMovies(apiKey: String) =
        RetrofitInstance.movieApi.getPopularMovies(apiKey)

}