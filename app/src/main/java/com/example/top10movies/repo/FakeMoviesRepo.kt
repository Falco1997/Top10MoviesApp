package com.example.top10movies.repo

import androidx.lifecycle.MutableLiveData
import com.example.top10movies.api.data.Genre
import com.example.top10movies.api.data.Movie
import com.example.top10movies.api.data.MovieDetails
import com.example.top10movies.api.data.MoviesResult

class FakeMoviesRepo {

    private val movie1 = Movie(
        "/5GA3vV1aWWHTSDO5eno8V5zDo8r.jpg",
        "1",
        "/path"
    )
    private val movie2 = Movie(
        "/iS9U3VHpPEjTWnwmW56CrBlpgLj.jpg",
        "12",
        "/path"
    )
    private val movie3 = Movie(
        "/7ze7YNmUaX81ufctGqt0AgHxRtL.jpg",
        "123",
        "/path"
    )
    private val movies = MoviesResult(
        listOf(movie1, movie2, movie3),
    )
    private val genre1 = Genre("1", "Horror")
    private val genre2 = Genre("2", "Science-Fiction")
    private val genre3 = Genre("3", "Fantasy")

    /*
    private val movieDetails1 = MovieDetails(
        "/5GA3vV1aWWHTSDO5eno8V5zDo8r.jpg",
        listOf(genre1,genre2,genre3),
        "1",
        "og_title",


    )
    */

    private val observableMovies = MutableLiveData(movies)
    private val observableMovieId = MutableLiveData<Movie>()
    private var shouldReturnNetworkError = false

    fun shouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    fun getPopularMovies() : MoviesResult {
        return movies
    }

    fun getMovieById(movieId: String) {
        //return movieDetails1
    }



}
