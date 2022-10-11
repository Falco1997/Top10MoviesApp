package com.example.top10movies.repo

import com.example.top10movies.api.data.MovieDetails
import com.example.top10movies.api.data.MoviesResult

class FakeMoviesRepo {

    private val movies = mutableListOf<MoviesResult>()
    //private val movieDetails = MovieDetails()

    fun getPopularMovies() : List<MoviesResult> {
        return movies
    }

    /*
    fun getMovieById(id: String): MovieDetails {
        return movieDetails
    }
    */

}