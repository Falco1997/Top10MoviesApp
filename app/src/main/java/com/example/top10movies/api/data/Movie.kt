package com.example.top10movies.api.data

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("") val adult: Boolean,
    @SerializedName("") val backdrop_path: String,
    @SerializedName("") val genre_ids: List<Int>,
    @SerializedName("") val id: Int,
    @SerializedName("") val media_type: String,
    @SerializedName("") val original_language: String,
    @SerializedName("") val original_title: String,
    @SerializedName("") val overview: String,
    @SerializedName("") val popularity: Double,
    @SerializedName("") val poster_path: String,
    @SerializedName("") val release_date: String,
    @SerializedName("") val title: String,
    @SerializedName("") val video: Boolean,
    @SerializedName("") val vote_average: Double,
    @SerializedName("") val vote_count: Int
)