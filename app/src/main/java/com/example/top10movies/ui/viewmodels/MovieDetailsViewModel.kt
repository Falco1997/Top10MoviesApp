package com.example.top10movies.ui.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.top10movies.Top10MoviesApp
import com.example.top10movies.api.data.MovieDetails
import com.example.top10movies.repo.MovieDetailsRepo
import com.example.top10movies.util.Constants.Companion.API_KEY
import com.example.top10movies.util.Constants.Companion.JSON_PARSING_ERROR
import com.example.top10movies.util.Constants.Companion.NETWORK_FAILURE
import com.example.top10movies.util.Constants.Companion.NO_INTERNET
import com.example.top10movies.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class MovieDetailsViewModel(
    app: Application,
    val movieDetailsRepo: MovieDetailsRepo,
    movieId: String
    ) : AndroidViewModel(app) {
    val movieDetails: MutableLiveData<Resource<MovieDetails>> = MutableLiveData()

    init {
        getMovieDetailsById(API_KEY, movieId)
    }

    fun getMovieDetailsById(apiKey: String, movieId: String) = viewModelScope.launch {
        safeMovieDetailsCall(apiKey, movieId)
    }

    private fun handleMovieResult(response: Response<MovieDetails>) : Resource<MovieDetails> {
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
                val response = movieDetailsRepo.getMovieDetailsById(apiKey, movieId)
                movieDetails.postValue(handleMovieResult(response))
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
