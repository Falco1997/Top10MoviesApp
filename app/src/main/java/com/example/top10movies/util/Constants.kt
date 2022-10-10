package com.example.top10movies.util

class Constants {

    companion object {
        // API
        const val API_KEY = "df0c4e1e50f6dbe36518ea582d869421"
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val GLIDE_BASE_URL_LARGE = "https://image.tmdb.org/t/p/w500/"
        const val GLIDE_BASE_URL_SMALL = "https://image.tmdb.org/t/p/w185/"

        // Activity traversing
        const val MOVIE_ID_KEY = "MOVIE_ID_KEY"

        // Error texts
        const val NO_INTERNET = "No internet connection"
        const val NETWORK_FAILURE = "Network Failure"
        const val JSON_PARSING_ERROR = "JSON Parsing Error"

        // Texts
        const val WHITE_SPACE = " "
    }

}