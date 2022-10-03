package com.example.top10movies.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.top10movies.repo.MoviesRepo

class MovieViewModelProviderFactory(
    val app: Application,
    val moviesRepo: MoviesRepo
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Application::class.java, MoviesRepo::class.java)
            .newInstance(app, moviesRepo)
    }
}