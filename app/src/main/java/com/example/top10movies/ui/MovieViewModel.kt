package com.example.top10movies.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.top10movies.api.data.MoviesResult
import com.example.top10movies.repo.MovieRepo
import com.example.top10movies.util.Constants.Companion.API_KEY
import com.example.top10movies.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieViewModel(
    val movieRepo: MovieRepo
) : ViewModel() {

    val trendingMovies: MutableLiveData<Resource<MoviesResult>> = MutableLiveData()
    var page = 1

    init {
        getTrendingMovies(API_KEY)
    }

    fun getTrendingMovies(apiKey: String) = viewModelScope.launch {
        trendingMovies.postValue(Resource.Loading())
        val response = movieRepo.getTrendingMovies(apiKey)
        trendingMovies.postValue(handleMovieResult(response))
    }

    private fun handleMovieResult(response: Response<MoviesResult>) : Resource<MoviesResult> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                Log.d("debug", "movieList size: " + resultResponse.movies.size.toString())
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}