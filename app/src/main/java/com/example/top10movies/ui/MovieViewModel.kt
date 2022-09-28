package com.example.top10movies.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.top10movies.Top10MoviesApp
import com.example.top10movies.api.data.MoviesResult
import com.example.top10movies.repo.MovieRepo
import com.example.top10movies.util.Constants.Companion.API_KEY
import com.example.top10movies.util.Constants.Companion.JSON_PARSING_ERROR
import com.example.top10movies.util.Constants.Companion.NETWORK_FAILURE
import com.example.top10movies.util.Constants.Companion.NO_INTERNET
import com.example.top10movies.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class MovieViewModel(
    app: Application,
    val movieRepo: MovieRepo
) : AndroidViewModel(app) {

    val popularMovies: MutableLiveData<Resource<MoviesResult>> = MutableLiveData()

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
        popularMovies.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = movieRepo.getTrendingMovies(apiKey)
                popularMovies.postValue(handleMovieResult(response))
            } else {
                popularMovies.postValue(Resource.Error(NO_INTERNET))
            }
        } catch (t: Throwable) {
            when(t) {
                is IOException -> popularMovies.postValue(Resource.Error(NETWORK_FAILURE))
                else -> popularMovies.postValue(Resource.Error(JSON_PARSING_ERROR))
            }
        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Top10MoviesApp>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            // For API Level < 23
            connectivityManager.activeNetworkInfo?.run {
                return when(type) {
                    TYPE_WIFI -> true
                    TYPE_MOBILE -> true
                    TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }

}