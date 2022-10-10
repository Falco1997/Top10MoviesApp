package com.example.top10movies.repo

import com.example.top10movies.api.RetrofitInstance

class MovieDetailsRepo {

    suspend fun getMovieDetailsById(apiKey: String, movieId: String) =
        RetrofitInstance.movieApi.getMovieDetailsById(apiKey, movieId)

}