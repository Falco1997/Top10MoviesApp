package com.example.top10movies.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.top10movies.repo.MovieRepo

class MovieViewModelProviderFactory(
    val app: Application,
    val movieRepo: MovieRepo
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Application::class.java, MovieRepo::class.java)
            .newInstance(app, movieRepo)
    }
}