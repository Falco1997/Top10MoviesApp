package com.example.top10movies.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.top10movies.repo.MovieRepo

class MovieViewModelProviderFactory(
    val movieRepo: MovieRepo
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepo::class.java)
            .newInstance(movieRepo)
    }
}