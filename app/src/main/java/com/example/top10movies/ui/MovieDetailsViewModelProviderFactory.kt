package com.example.top10movies.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.top10movies.repo.MovieDetailsRepo

class MovieDetailsViewModelProviderFactory(
    val app: Application,
    val movieDetailsRepo: MovieDetailsRepo,
    val movieId: String
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Application::class.java, MovieDetailsRepo::class.java)
            .newInstance(app, movieDetailsRepo, movieId)
    }
}