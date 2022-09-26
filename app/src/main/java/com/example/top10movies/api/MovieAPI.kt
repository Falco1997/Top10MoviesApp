package com.example.top10movies.api

import com.example.top10movies.api.data.MoviesResult
import com.example.top10movies.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET("trending/movie/week")
    suspend fun getTrendingMovies(
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<MoviesResult>

}