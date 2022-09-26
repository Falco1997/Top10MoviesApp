package com.example.top10movies.repo

import com.example.top10movies.api.RetrofitInstance

class MovieRepo {

    suspend fun getTrendingMovies(apiKey: String) =
        RetrofitInstance.movieApi.getTrendingMovies(apiKey)

}