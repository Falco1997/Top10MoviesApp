package com.example.top10movies.api.data

import com.example.top10movies.api.data.Movie
import com.google.gson.annotations.SerializedName

data class MoviesResult(
    //@SerializedName("page") val page: Int,
    @SerializedName("results") val movies: List<Movie>,
    //@SerializedName("total_pages") val total_pages: Int,
    //@SerializedName("total_results") val total_results: Int
)