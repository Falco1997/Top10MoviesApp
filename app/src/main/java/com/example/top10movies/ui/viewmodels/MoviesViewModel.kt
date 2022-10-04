package com.example.top10movies.ui.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.*
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.top10movies.Top10MoviesApp
import com.example.top10movies.api.data.MovieDetails
import com.example.top10movies.api.data.MoviesResult
import com.example.top10movies.repo.MoviesRepo
import com.example.top10movies.util.Constants.Companion.API_KEY
import com.example.top10movies.util.Constants.Companion.JSON_PARSING_ERROR
import com.example.top10movies.util.Constants.Companion.NETWORK_FAILURE
import com.example.top10movies.util.Constants.Companion.NO_INTERNET
import com.example.top10movies.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class MoviesViewModel(
    app: Application,
    val moviesRepo: MoviesRepo,
    //val movieDetailsRepo: MovieDetailsRepo
) : AndroidViewModel(app) {

    val movies: MutableLiveData<Resource<MoviesResult>> = MutableLiveData()
    val movieDetails: MutableLiveData<Resource<MovieDetails>> = MutableLiveData()

    init {
        getPopularMovies(API_KEY)
    }

    fun getPopularMovies(apiKey: String) = viewModelScope.launch {
        safeGetPopularMoviesCall(apiKey)
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

    private suspend fun safeGetPopularMoviesCall(apiKey: String) {
        movies.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = moviesRepo.getPopularMovies(apiKey)
                movies.postValue(handleMovieResult(response))
            } else {
                movies.postValue(Resource.Error(NO_INTERNET))
            }
        } catch (t: Throwable) {
            when(t) {
                is IOException -> movies.postValue(Resource.Error(NETWORK_FAILURE))
                else -> movies.postValue(Resource.Error(JSON_PARSING_ERROR))
            }
        }
    }

    fun getMovieDetailsById(apiKey: String, movieId: String) = viewModelScope.launch {
        safeMovieDetailsCall(apiKey, movieId)
    }

    private fun handleMovieDetailsResult(response: Response<MovieDetails>) : Resource<MovieDetails> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private suspend fun safeMovieDetailsCall(apiKey: String, movieId: String) {
        movieDetails.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = moviesRepo.getMovieDetailsById(apiKey, movieId)
                movieDetails.postValue(handleMovieDetailsResult(response))
            } else {
                movieDetails.postValue(Resource.Error(NO_INTERNET))
            }
        } catch (t: Throwable) {
            when(t) {
                is IOException -> movieDetails.postValue(Resource.Error(NETWORK_FAILURE))
                else -> movieDetails.postValue(Resource.Error(JSON_PARSING_ERROR))
            }
        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Top10MoviesApp>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(TRANSPORT_WIFI) -> true
            capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

}