package com.example.top10movies.api.data

import com.example.top10movies.api.data.Movie
import com.google.gson.annotations.SerializedName

data class GetMoviesResult(
    @SerializedName("") val page: Int,
    @SerializedName("") val movies: List<Movie>,
    @SerializedName("") val total_pages: Int,
    @SerializedName("") val total_results: Int
)